package com.iccd.storage.engine.core;

import com.iccd.storage.engine.core.context.*;

import java.io.IOException;

/**
 * 文件存储引擎的接口
 *
 */
public interface StorageEngine {

    /**
     * 存储物理文件
     * @param context
     * @throws IOException
     */
    void store(StoreRealFileContext context) throws IOException;

    /**
     * 删除物理文件
     * @param context
     * @throws IOException
     */
    void delete(DeleteRealFileContext context) throws IOException;

    /**
     * 存储物理文件分片
     * @param context
     * @throws IOException
     */
    void storeChunk(StoreFileChunkContext context) throws IOException;


    /**
     * 合并文件分片
     *
     * @param context
     * @throws IOException
     */
    void mergeFile(MergeFileChunkContext context) throws IOException;

    void readFile(ReadFileContext context) throws IOException;
}
