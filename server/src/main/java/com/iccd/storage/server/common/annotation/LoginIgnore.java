package com.iccd.storage.server.common.annotation;

import java.lang.annotation.*;

/**
 * 不需要登录即可访问的接口
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LoginIgnore {
}
