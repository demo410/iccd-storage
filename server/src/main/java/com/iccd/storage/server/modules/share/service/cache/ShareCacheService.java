package com.iccd.storage.server.modules.share.service.cache;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.iccd.storage.server.common.cache.AbstractManualCacheService;
import com.iccd.storage.server.modules.share.entity.Share;
import com.iccd.storage.server.modules.share.mapper.ShareMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShareCacheService extends AbstractManualCacheService<Share> {

    @Autowired
    private ShareMapper mapper;

    @Override
    protected BaseMapper getBaseMapper() {
        return mapper;
    }

    @Override
    public String getKeyFormat() {
        return "SHARE:ID:%s";
    }
}
