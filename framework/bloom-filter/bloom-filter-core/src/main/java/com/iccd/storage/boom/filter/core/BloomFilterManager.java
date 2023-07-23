package com.iccd.storage.boom.filter.core;

import java.util.Collection;

public interface BloomFilterManager {

    /**
     * 根据名称获取对应的布隆过滤器
     *
     * @param name
     * @return
     */
    BloomFilter getFilter(String name);

    /**
     * 获取目前管理中存在的布隆过滤器名称列表
     *
     * @return
     */
    Collection<String> getFilterNames();
}
