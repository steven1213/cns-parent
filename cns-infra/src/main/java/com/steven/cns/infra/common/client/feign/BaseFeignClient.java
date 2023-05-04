package com.steven.cns.infra.common.client.feign;

import lombok.extern.slf4j.Slf4j;

import java.util.function.Supplier;

/**
 * @author steven.cao
 * example:
 * @FeignClient(name = "user-service", configuration = UserServiceClient.Config.class)
 * public interface UserServiceClient extends BaseFeignClient<UserServiceClient> {
 *     @GetMapping("/users/{userId}")
 *     UserDTO getUser(@PathVariable("userId") Long userId);
 *
 *     class Config {
 *         @Bean
 *         public RequestInterceptor traceIdInterceptor() {
 *             String traceId = MdcUtils.setTraceIdIfAbsent();
 *             return new TraceIdInterceptor(traceId);
 *         }
 *     }
 * }
 *
 */
@Slf4j
public abstract class BaseFeignClient<T> {
    private final T client;
    private final int maxRetries;
    private final long initialInterval;
    private final long maxInterval;
    private final double multiplier;

    public BaseFeignClient(T client, int maxRetries, long initialInterval, long maxInterval, double multiplier) {
        this.client = client;
        this.maxRetries = maxRetries;
        this.initialInterval = initialInterval;
        this.maxInterval = maxInterval;
        this.multiplier = multiplier;
    }

    public T getClient() {
        return client;
    }

    protected <R> R execute(String methodName, Supplier<R> supplier) {
        int retries = 0;
        long interval = initialInterval;
        while (true) {
            try {
                log.info("Executing method {} with {} retries", methodName, retries);
                R result = supplier.get();
                log.info("Method {} executed successfully", methodName);
                return result;
            } catch (Exception e) {
                log.error("Method {} failed to execute", methodName, e);
                if (retries < maxRetries) {
                    retries++;
                    log.info("Retrying method {} after {} ms", methodName, interval);
                    try {
                        Thread.sleep(interval);
                    } catch (InterruptedException ex) {
                    }
                    interval = (long) (interval * multiplier);
                    interval = Math.min(interval, maxInterval);
                } else {
                    log.error("Method {} failed to execute after {} retries", methodName, retries);
                    throw e;
                }
            }
        }
    }
}
