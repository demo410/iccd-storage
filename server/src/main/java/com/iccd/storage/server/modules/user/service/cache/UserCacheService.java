package com.iccd.storage.server.modules.user.service.cache;

import com.iccd.storage.cache.core.constant.CacheConstants;
import com.iccd.storage.server.common.cache.AnnotationCacheService;
import com.iccd.storage.server.modules.user.entity.User;
import com.iccd.storage.server.modules.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class UserCacheService implements AnnotationCacheService<User> {

    @Autowired
    private UserMapper mapper;

    /**
     * 根据ID查询实体
     *
     * @param id
     * @return
     */
    @Cacheable(cacheNames = CacheConstants.ICCD_STORAGE_CACHE_NAME, keyGenerator = "userIdKeyGenerator", sync = true)
    @Override
    public User getById(Serializable id) {
        return mapper.selectById(id);
    }

    /**
     * 根据ID来更新缓存信息
     *
     * @param id
     * @param entity
     * @return
     */
    @CacheEvict(cacheNames = CacheConstants.ICCD_STORAGE_CACHE_NAME, keyGenerator = "userIdKeyGenerator")
    @Override
    public boolean updateById(Serializable id, User entity) {
        return mapper.updateById(entity) == 1;
    }

    /**
     * 根据ID来删除缓存信息
     *
     * @param id
     * @return
     */
    @CacheEvict(cacheNames = CacheConstants.ICCD_STORAGE_CACHE_NAME, keyGenerator = "userIdKeyGenerator")
    @Override
    public boolean removeById(Serializable id) {
        return mapper.deleteById(id) == 1;
    }
}
