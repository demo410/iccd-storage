package com.iccd.storage.server.common.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

public interface BloomFilterInterceptor extends HandlerInterceptor {

    /**
     * 拦截器的名称
     *
     * @return
     */
    String getName();

    /**
     * 要拦截的URI的集合
     *
     * @return
     */
    String[] getPathPatterns();

    /**
     * 要排除拦截的URI的集合
     *
     * @return
     */
    String[] getExcludePatterns();
}
