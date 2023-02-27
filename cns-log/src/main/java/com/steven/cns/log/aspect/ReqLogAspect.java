package com.steven.cns.log.aspect;

import com.steven.cns.infra.utils.GsonUtils;
import com.steven.cns.infra.utils.ServletUtils;
import com.steven.cns.log.annotation.OperationLog;
import com.steven.cns.log.annotation.ReqLog;
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
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author steven.cao
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
        ReqLogModel reqLogModel = handleReqLog(joinPoint, null, result);
        if (Objects.isNull(reqLogModel)) {
            return;
        }
        Long end = System.currentTimeMillis();
        log.info("[Req-Log]:耗时:[{}]ms,请求参数:{}", (end - begin), GsonUtils.toJsonAllowNull(reqLogModel));
    }

    @AfterThrowing(pointcut = "reqLogPointCut()", throwing = "ex")
    public void doAfterReturning(JoinPoint joinPoint, Exception ex) {
        Long begin = System.currentTimeMillis();
        ReqLogModel reqLogModel = handleReqLog(joinPoint, ex, null);
        if (Objects.isNull(reqLogModel)) {
            return;
        }
        Long end = System.currentTimeMillis();
        log.info("[ReqLog]:耗时:[{}]ms,请求参数:{}", (end - begin), GsonUtils.prettyPrint(reqLogModel));
    }

    private ReqLogModel handleReqLog(JoinPoint joinPoint, Exception ex, Object o) {
        ReqLog operationLog = getAnnotationLog(joinPoint);
        if (null == operationLog) {
            return null;
        }

        if (operationLog.printHeader()) {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            Iterator<String> headerNameIterator = request.getHeaderNames().asIterator();
            Map<String, String> headerMap = new HashMap<>();
            while (headerNameIterator.hasNext()) {
                String headerName = headerNameIterator.next();
                headerMap.put(headerName, request.getHeader(headerName));
            }
            log.info("[ReqLog] header参数:{}", GsonUtils.toJson(headerMap));
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
            model.setRequestResult(GsonUtils.toJson(o));
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

    private ReqLog getAnnotationLog(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if (null != method) {
            return method.getAnnotation(ReqLog.class);
        }
        return null;
    }
}
