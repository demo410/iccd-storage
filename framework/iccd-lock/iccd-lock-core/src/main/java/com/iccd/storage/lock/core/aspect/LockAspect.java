package com.iccd.storage.lock.core.aspect;

import com.iccd.storage.core.exception.StorageFrameworkException;
import com.iccd.storage.lock.core.LockContext;
import com.iccd.storage.lock.core.key.KeyGenerator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.integration.support.locks.LockRegistry;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

@Component
@Aspect
@Slf4j
public class LockAspect implements ApplicationContextAware {

    @Autowired
    ApplicationContext applicationContext;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Autowired
    private LockRegistry lockRegistry;

    @Pointcut(value = "@annotation(com.iccd.storage.lock.core.annotation.Lock)")
    public void lockPointcut(){

    }

    @Around("lockPointcut()")
    public Object aroundLock(ProceedingJoinPoint proceedingJoinPoint){
        LockContext lockContext = LockContext.init(proceedingJoinPoint);

        Lock lock = checkAndGetLock(lockContext);
        Object result = null;

        if (Objects.isNull(lock)){
            log.error("lock aspect get lock fail.");
            throw new StorageFrameworkException("aroundLock get lock fail");
        }
        boolean lockSuccess = false;
        try {
            lockSuccess = lock.tryLock(lockContext.getAnnotation().expireSecond(), TimeUnit.SECONDS);
            if (lockSuccess){
                result = proceedingJoinPoint.proceed();
            }
        } catch (Throwable e) {
            log.error("lock aspect tryLock exception.", e);
            throw new StorageFrameworkException("aroundLock tryLock fail.");
        } finally {
            if (lockSuccess){
                lock.unlock();
            }
        }

        return result;
    }

    private Lock checkAndGetLock(LockContext lockContext) {
        if (Objects.isNull(lockRegistry)) {
            log.error("the lockRegistry is not found...");
            return null;
        }
        String lockKey = getLockKey(lockContext);
        if (StringUtils.isBlank(lockKey)) {
            return null;
        }
        return lockRegistry.obtain(lockKey);
    }

    /**
     * 获取锁key的私有方法
     *
     * @param lockContext
     * @return
     */
    private String getLockKey(LockContext lockContext) {
        KeyGenerator keyGenerator = applicationContext.getBean(lockContext.getAnnotation().keyGenerator());
        if (Objects.nonNull(keyGenerator)) {
            return keyGenerator.generateKey(lockContext);
        }
        log.error("the keyGenerator is not found...");
        return StringUtils.EMPTY;
    }

}
