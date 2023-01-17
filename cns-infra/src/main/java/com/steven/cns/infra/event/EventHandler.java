package com.steven.cns.infra.event;

/**
 * @author steven.cao
 */
public interface EventHandler {

    /**
     * 执行event
     *
     * @param context context
     * @param <R>     返回
     * @return R
     */
    <R> R execute(EventContext context);
}
