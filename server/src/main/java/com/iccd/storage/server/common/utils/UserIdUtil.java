package com.iccd.storage.server.common.utils;

import com.iccd.storage.core.constants.StorageConstants;

import java.util.Objects;

public class UserIdUtil {
    private static final ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void set(Long userId){
        threadLocal.set(userId);
    }

    public static Long get(){
        Long userId = threadLocal.get();

        if (Objects.isNull(userId)){
            return StorageConstants.ZERO_LONG;
        }

        return userId;
    }

    public static void remove(){
        threadLocal.remove();
    }
}
