package com.iccd.storage.engine.local.initializer;

import com.iccd.storage.engine.local.config.StorageEngineLocalConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
@Slf4j
public class UploadFolderAndChunksFolderInitializer implements CommandLineRunner {

    @Autowired
    private StorageEngineLocalConfig config;

    @Override
    public void run(String... args) throws Exception {
        FileUtils.forceMkdir(new File(config.getRootFilePath()));
        log.info("the root file path has been created");
        FileUtils.forceMkdir(new File(config.getRootFileChunkPath()));
    }
}
