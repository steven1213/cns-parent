package com.steven.cns.infra.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.*;

/**
 * redisson 工具类
 *
 * @author steven.cao
 */
@Slf4j
public class RedissonUtils {

    private static RedissonClient redissonClient;

    public static void init(RedissonClient redissonClient) {
        RedissonUtils.redissonClient = redissonClient;
    }

    public static <T> RMap<String, T> getMap(String name) {
        return redissonClient.getMap(name);
    }

    public static <T> RList<T> getList(String name) {
        return redissonClient.getList(name);
    }

    public static <T> RSet<T> getSet(String name) {
        return redissonClient.getSet(name);
    }

    public static <T> RSortedSet<T> getSortedSet(String name) {
        return redissonClient.getSortedSet(name);
    }

    public static <T> RBucket<T> getBucket(String name) {
        return redissonClient.getBucket(name);
    }

    public static <T> RHyperLogLog<T> getHyperLogLog(String name) {
        return redissonClient.getHyperLogLog(name);
    }

    public static <T> RTopic getTopic(String name) {
        return redissonClient.getTopic(name);
    }

    public static <T> RQueue<T> getQueue(String name) {
        return redissonClient.getQueue(name);
    }

    public static <T> RBlockingQueue<T> getBlockingQueue(String name) {
        return redissonClient.getBlockingQueue(name);
    }

    public static <T> RDeque<T> getDeque(String name) {
        return redissonClient.getDeque(name);
    }

    public static <V> RLock getLock(String name) {
        return redissonClient.getLock(name);
    }

    public static <V> RLock getFairLock(String name) {
        return redissonClient.getFairLock(name);
    }

    public static <V> RReadWriteLock getReadWriteLock(String name) {
        return redissonClient.getReadWriteLock(name);
    }

    public static <V> RCountDownLatch getCountDownLatch(String name) {
        return redissonClient.getCountDownLatch(name);
    }

    public static <V> RSemaphore getSemaphore(String name) {
        return redissonClient.getSemaphore(name);
    }

    public static <V> RPermitExpirableSemaphore getPermitExpirableSemaphore(String name) {
        return redissonClient.getPermitExpirableSemaphore(name);
    }

    public static <V> RAtomicLong getAtomicLong(String name) {
        return redissonClient.getAtomicLong(name);
    }

    public static <V> RDoubleAdder getDoubleAdder(String name) {
        return redissonClient.getDoubleAdder(name);
    }

    public static <V> RLongAdder getLongAdder(String name) {
        return redissonClient.getLongAdder(name);
    }

    public static <V> RAtomicDouble getAtomicDouble(String name) {
        return redissonClient.getAtomicDouble(name);
    }


    public static <V> RBitSet getBitSet(String name) {
        return redissonClient.getBitSet(name);
    }

    public static <V> RBloomFilter getBloomFilter(String name) {
        return redissonClient.getBloomFilter(name);
    }

    // 释放锁
    public static void unlock(String lockKey) {
        RLock lock = getLock(lockKey);
        if (lock.isLocked()) {
            lock.unlock();
        }
    }

    // 释放锁
    public static void unlock(RLock lock) {
        if (lock.isLocked()) {
            lock.unlock();
        }
    }
}
