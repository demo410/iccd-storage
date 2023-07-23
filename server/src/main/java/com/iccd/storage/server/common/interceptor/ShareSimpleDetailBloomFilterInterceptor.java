package com.iccd.storage.server.common.interceptor;


import com.iccd.storage.boom.filter.core.BloomFilter;
import com.iccd.storage.boom.filter.core.BloomFilterManager;
import com.iccd.storage.core.exception.StorageBusinessException;
import com.iccd.storage.core.response.ResponseCode;
import com.iccd.storage.core.utils.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Component
@Slf4j
public class ShareSimpleDetailBloomFilterInterceptor implements BloomFilterInterceptor{
    @Autowired
    private BloomFilterManager manager;

    private static final String BLOOM_FILTER_NAME = "SHARE_SIMPLE_DETAIL";
    @Override
    public String getName() {
        return "ShareSimpleDetailBloomFilterInterceptor";
    }

    @Override
    public String[] getPathPatterns() {
        return ArrayUtils.toArray("/share/simple");
    }

    @Override
    public String[] getExcludePatterns() {
        return new String[0];
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String shareId = request.getParameter("shareId");

        if (StringUtils.isBlank(shareId)){
            throw new StorageBusinessException("分享ID不能为空");
        }
        BloomFilter filter = manager.getFilter(BLOOM_FILTER_NAME);
        if (Objects.isNull(filter)){
            log.info("布隆过滤器为空：{}", BLOOM_FILTER_NAME);
        }
        Long decryptShareId = IdUtil.decrypt(shareId);
        if (filter.mightContain(decryptShareId)){
            return true;
        }

        throw new StorageBusinessException(ResponseCode.SHARE_CANCELLED);
    }
}
