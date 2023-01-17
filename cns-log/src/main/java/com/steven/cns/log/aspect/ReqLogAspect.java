package com.steven.cns.log.aspect;

import com.steven.cns.infra.utils.GsonUtils;
import com.steven.cns.infra.utils.ServletUtils;
import com.steven.cns.log.annotation.OperationLog;
import com.steven.cns.log.model.ReqLogModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author dr.panda
 */
@Slf4j
@Aspect
@Component
public class ReqLogAspect {

    @Pointcut("@annotation(com.steven.cns.log.annotation.ReqLog)")
    public void reqLogPointCut() {
    }

    @AfterReturning(pointcut = "reqLogPointCut()", returning = "result")
    public void doAfterReturning(JoinPoint joinPoint, Object result) {
        Long begin = System.currentTimeMillis();
        ReqLogModel reqLogModel = handleOperationLog(joinPoint, null, result);
        if (Objects.isNull(reqLogModel)) {
            return;
        }
        Long end = System.currentTimeMillis();
        log.info("[Req-Log]:耗时:[{}]ms,请求参数:{}", (end - begin), GsonUtils.toJsonAllowNull(reqLogModel));
    }

    @AfterThrowing(pointcut = "reqLogPointCut()", throwing = "ex")
    public void doAfterReturning(JoinPoint joinPoint, Exception ex) {
        Long begin = System.currentTimeMillis();
        ReqLogModel reqLogModel = handleOperationLog(joinPoint, ex, null);
        if (Objects.isNull(reqLogModel)) {
            return;
        }
        Long end = System.currentTimeMillis();
        log.info("[Req-Log]:耗时:[{}]ms,请求参数:{}", (end - begin), GsonUtils.toJsonAllowNull(reqLogModel));
    }

    private ReqLogModel handleOperationLog(JoinPoint joinPoint, Exception ex, Object o) {
        OperationLog operationLog = getAnnotationLog(joinPoint);
        if (null == operationLog) {
            return null;
        }
        ReqLogModel model = new ReqLogModel();
        handlerRequestUri(model);
        handlerRequestError(ex, model);
        handleLogMethod(joinPoint, model);
        handleLogResp(o, model);
        return model;
    }

    private void handleLogResp(Object o, ReqLogModel model) {
        if (Objects.nonNull(o)) {
            model.setRequestResult(o.toString());
        }
    }


    private void handlerRequestUri(ReqLogModel logModel) {
        String uri = ServletUtils.getRequest().getRequestURI();
        logModel.setRequestUrl(uri);
    }

    private void handlerRequestError(Exception ex, ReqLogModel logModel) {
        String requestError;
        if (Objects.nonNull(ex)) {
            requestError = StringUtils.substring(ex.getMessage(), 0, 2000);
            logModel.setRequestError(requestError);
        }
    }

    private void handleLogMethod(JoinPoint joinPoint, ReqLogModel logModel) {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        String method = className + "." + methodName + "()";
        logModel.setMethod(method);
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
