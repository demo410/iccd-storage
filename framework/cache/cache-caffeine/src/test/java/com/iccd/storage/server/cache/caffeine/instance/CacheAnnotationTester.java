package com.iccd.storage.server.cache.caffeine.instance;

import com.iccd.storage.cache.core.constant.CacheConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CacheAnnotationTester {

    @Cacheable(cacheNames = CacheConstants.ICCD_STORAGE_CACHE_NAME, key = "#name", sync = true)
    public String testCacheable(String name){
      log.info(name);
      return new StringBuilder("hello ").append(name).toString();
    }
}
