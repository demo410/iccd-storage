package com.iccd.storage.engine.local;

import com.iccd.storage.core.utils.FileUtils;
import com.iccd.storage.engine.core.AbstractStorageEngine;
import com.iccd.storage.engine.core.context.*;
import com.iccd.storage.engine.local.config.StorageEngineLocalConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@Component
public class StorageEngineLocal extends AbstractStorageEngine {

    @Autowired
    StorageEngineLocalConfig config;

    @Override
    protected void doStore(StoreRealFileContext context) throws IOException {
        String realFilePath = FileUtils.generateStoreFileRealPath(config.getRootFilePath(), context.getFilename());
        context.setRealPath(realFilePath);
        FileUtils.writeStream2File(context.getInputStream(),
                new File(realFilePath), context.getTotalSize());
    }

    @Override
    protected void doDelete(DeleteRealFileContext context) throws IOException {
        FileUtils.deleteFiles(context.getRealFilePathList());
    }

    @Override
    protected void doStoreChunk(StoreFileChunkContext context) throws IOException {
        String realFilePath = FileUtils.generateStoreFileChunkRealPath(config.getRootFileChunkPath(),
                context.getIdentifier(), context.getChunkNumber());

        context.setRealPath(realFilePath);
        FileUtils.writeStream2File(context.getInputStream(),
                new File(realFilePath), context.getTotalSize());
    }

    @Override
    protected void doMergeFile(MergeFileChunkContext context) throws IOException {

        String realFilePath = FileUtils.generateStoreFileRealPath(config.getRootFilePath(), context.getFilename());
        context.setRealPath(realFilePath);
        FileUtils.createFile(new File(realFilePath));
        List<String> fileChunkRealPathList = context.getRealPathList();

        /**
         * 使用追加写的方式合并文件
         */
        for (String chunkPath : fileChunkRealPathList) {
            FileUtils.appendWrite(Paths.get(realFilePath), new File(chunkPath).toPath());
            //删除文件？
        }

        context.setRealPath(realFilePath);
    }

    @Override
    protected void doReadFile(ReadFileContext context) throws IOException {
        File file = new File(context.getRealPath());

        FileUtils.writeFile2OutputStream(new FileInputStream(file), context.getOutputStream(), file.length());
    }
}
