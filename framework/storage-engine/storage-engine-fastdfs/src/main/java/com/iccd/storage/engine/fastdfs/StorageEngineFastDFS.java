package com.iccd.storage.engine.fastdfs;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.iccd.storage.core.constants.StorageConstants;
import com.iccd.storage.core.exception.StorageFrameworkException;
import com.iccd.storage.core.utils.FileUtils;
import com.iccd.storage.engine.core.AbstractStorageEngine;
import com.iccd.storage.engine.core.context.*;
import com.iccd.storage.engine.fastdfs.config.FastDFSStorageEngineConfig;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Component
public class StorageEngineFastDFS extends AbstractStorageEngine {

    @Autowired
    FastDFSStorageEngineConfig config;

    @Autowired
    FastFileStorageClient client;

    @Override
    protected void doStore(StoreRealFileContext context) {
        StorePath storePath = client.uploadFile(config.getGroup(), context.getInputStream(), context.getTotalSize(),
                FileUtils.getFileExtName(context.getFilename()));
        context.setRealPath(storePath.getFullPath());
    }

    @Override
    protected void doDelete(DeleteRealFileContext context) {
        List<String> realFilePathList = context.getRealFilePathList();
        if (CollectionUtils.isNotEmpty(realFilePathList)) {
            realFilePathList.stream().forEach(client::deleteFile);
        }
    }

    @Override
    protected void doStoreChunk(StoreFileChunkContext context) {
        throw new StorageFrameworkException("FastDFS不支持分片上传的操作");
    }

    @Override
    protected void doMergeFile(MergeFileChunkContext context) throws IOException {
        throw new StorageFrameworkException("FastDFS不支持分片上传的操作");
    }

    @Override
    protected void doReadFile(ReadFileContext context) throws IOException {
        String realPath = context.getRealPath();
        String group = realPath.substring(StorageConstants.ZERO_INT, realPath.indexOf(StorageConstants.SLASH));
        String path = realPath.substring(realPath.indexOf(StorageConstants.SLASH) + StorageConstants.ONE_INT);

        DownloadByteArray downloadByteArray = new DownloadByteArray();
        byte[] bytes = client.downloadFile(group, path, downloadByteArray);

        OutputStream outputStream = context.getOutputStream();
        outputStream.write(bytes);
        outputStream.flush();
        outputStream.close();
    }
}
