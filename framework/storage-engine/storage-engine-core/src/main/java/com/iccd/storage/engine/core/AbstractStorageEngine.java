package com.iccd.storage.engine.core;

import cn.hutool.core.lang.Assert;
import com.iccd.storage.cache.core.constant.CacheConstants;
import com.iccd.storage.core.exception.StorageFrameworkException;
import com.iccd.storage.engine.core.context.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import java.io.IOException;
import java.util.Objects;

/**
 * 顶级文件存储引擎的公用父类
 */
public abstract class AbstractStorageEngine implements StorageEngine {

    @Autowired
    CacheManager cacheManager;

    public Cache getCache() {

        if (Objects.isNull(cacheManager)) {
            throw new StorageFrameworkException("The cache manager is null");
        }
        return cacheManager.getCache(CacheConstants.ICCD_STORAGE_CACHE_NAME);
    }

    /**
     * 1. 参数校验
     * 2. 执行动作
     * @param context
     * @throws IOException
     */
    @Override
    public void store(StoreRealFileContext context) throws IOException {
        Assert.notNull(context.getFilename(), "文件名称不能为空");
        Assert.notNull(context.getTotalSize(), "文件大小不能为空");
        Assert.notNull(context.getInputStream(), "文件不能为空");

        doStore(context);
    }

    protected abstract void doStore(StoreRealFileContext context) throws IOException;

    @Override
    public void delete(DeleteRealFileContext context) throws IOException {

        Assert.notEmpty(context.getRealFilePathList(), "删除文件参数列表不能为空");
        doDelete(context);
    }

    protected abstract void doDelete(DeleteRealFileContext context) throws IOException;

    @Override
    public void storeChunk(StoreFileChunkContext context) throws IOException {
        Assert.notBlank(context.getFilename(), "文件名称不能为空");
        Assert.notBlank(context.getIdentifier(), "文件唯一标识不能为空");
        Assert.notNull(context.getTotalSize(), "文件大小不能为空");
        Assert.notNull(context.getInputStream(), "文件分片不能为空");
        Assert.notNull(context.getTotalChunks(), "文件分片总数不能为空");
        Assert.notNull(context.getChunkNumber(), "文件分片下标不能为空");
        Assert.notNull(context.getCurrentChunkSize(), "文件分片的大小不能为空");
        Assert.notNull(context.getUserId(), "当前登录用户的ID不能为空");

        doStoreChunk(context);
    }

    protected abstract void doStoreChunk(StoreFileChunkContext context) throws IOException;

    @Override
    public void mergeFile(MergeFileChunkContext context) throws IOException {
        Assert.notBlank(context.getFilename(), "文件名称不能为空");
        Assert.notBlank(context.getIdentifier(), "文件唯一标识不能为空");
        Assert.notNull(context.getUserId(), "当前登录用户的ID不能为空");
        Assert.notEmpty(context.getRealPathList(), "文件分片列表不能为空");

        doMergeFile(context);
    }

    protected abstract void doMergeFile(MergeFileChunkContext context) throws IOException;

    @Override
    public void readFile(ReadFileContext context) throws IOException {
        Assert.notBlank(context.getRealPath(), "文件真实存储路径不能为空");
        Assert.notNull(context.getOutputStream(), "文件的输出流不能为空");

        doReadFile(context);
    }

    protected abstract void doReadFile(ReadFileContext context) throws IOException;
}
