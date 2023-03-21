package com.steven.cns.log.aspect;

import com.google.gson.JsonObject;
import com.steven.cns.infra.common.resp.Resp;
import com.steven.cns.infra.utils.GsonUtils;
import com.steven.cns.infra.utils.ServletUtils;
import com.steven.cns.log.OperationLogHandler;
import com.steven.cns.log.annotation.OperationLog;
import com.steven.cns.log.model.OperationLogModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author steven.cao
 */
@Slf4j
@Aspect
@Component
public class OperationLogAspect {

    /**
     * 是否保存操作日志 默认不保存
     */
    @Value("${cns.operation.log.save-flag:false}")
    private boolean saveFlag;

    @Resource
    private OperationLogHandler operationLogHandler;

    @Pointcut("@annotation(com.steven.cns.log.annotation.OperationLog)")
    public void operationLogPointCut() {
    }

    @AfterReturning(pointcut = "operationLogPointCut()", returning = "result")
    public void doAfterReturning(JoinPoint joinPoint, Object result) {
        handleOperationLog(joinPoint, null, result);
    }

    @AfterThrowing(pointcut = "operationLogPointCut()", throwing = "ex")
    public void doAfterReturning(JoinPoint joinPoint, Exception ex) {
        handleOperationLog(joinPoint, ex, null);
    }

    private void handleOperationLog(JoinPoint joinPoint, Exception ex, Object o) {
        OperationLog operationLog = getAnnotationLog(joinPoint);
        if (null == operationLog) {
            return;
        }
        OperationLogModel logModel = assembleOperationLog(joinPoint, ex, o, operationLog);

        if (saveFlag) {
            if (ObjectUtils.isNotEmpty(operationLogHandler)) {
                operationLogHandler.saveOperationLog(logModel);
            }
        }
        if (log.isInfoEnabled()) {
            log.info("[OperationLog]:{}", GsonUtils.toJsonAllowNull(logModel));
        } else {
            log.info("[OperationLog]:\n{}", GsonUtils.prettyPrint(logModel));
        }

    }

    private OperationLogModel assembleOperationLog(JoinPoint joinPoint, Exception ex, Object o, OperationLog operationLog) {
        OperationLogModel logModel = new OperationLogModel();
        handlerRequestUri(logModel);
        handlerRequestError(ex, logModel);
        handleCommonResp(o, logModel);
        handleLogMethod(joinPoint, logModel);
        handleLogAnnotationParams(logModel, operationLog);
        return logModel;
    }

    private void handlerRequestUri(OperationLogModel logModel) {
        String uri = ServletUtils.getRequest().getRequestURI();
        logModel.setRequestUrl(uri);
    }

    private void handlerRequestError(Exception ex, OperationLogModel logModel) {
        String requestError;
        if (Objects.nonNull(ex)) {
            requestError = StringUtils.substring(ex.getMessage(), 0, 2000);
            logModel.setRequestError(requestError);
        }
    }

    private void handleCommonResp(Object o, OperationLogModel logModel) {
        if (Objects.nonNull(o)) {
            Resp<?> resp = GsonUtils.fromJson(GsonUtils.toJson(o), Resp.class);
            JsonObject json = new JsonObject();
            json.addProperty("code", resp.getCode());
            json.addProperty("msg", resp.getMsg());
            logModel.setSimpleResult(GsonUtils.toJson(json));
        }
    }

    private void handleLogMethod(JoinPoint joinPoint, OperationLogModel logModel) {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        String method = className + "." + methodName + "()";
        logModel.setMethod(method);
    }

    private void handleLogAnnotationParams(OperationLogModel logModel, OperationLog operationLog) {
        logModel.setAppName(operationLog.appName());
        logModel.setModule(operationLog.module());
        logModel.setDescription(operationLog.description());
    }

    private OperationLog getAnnotationLog(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if (null != method) {
            return method.getAnnotation(OperationLog.class);
        }
        return null;
    }
}
