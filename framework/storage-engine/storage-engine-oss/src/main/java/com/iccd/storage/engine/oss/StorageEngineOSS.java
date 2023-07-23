package com.iccd.storage.engine.oss;

import com.iccd.storage.engine.core.AbstractStorageEngine;
import com.iccd.storage.engine.core.context.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class StorageEngineOSS extends AbstractStorageEngine {
    @Override
    protected void doStore(StoreRealFileContext context) {

    }

    @Override
    protected void doDelete(DeleteRealFileContext context) {

    }

    @Override
    protected void doStoreChunk(StoreFileChunkContext context) {

    }

    @Override
    protected void doMergeFile(MergeFileChunkContext context) throws IOException {

    }

    @Override
    protected void doReadFile(ReadFileContext context) throws IOException {

    }
}
