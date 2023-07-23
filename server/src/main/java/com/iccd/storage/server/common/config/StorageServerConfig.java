package com.iccd.storage.server.common.config;

import com.iccd.storage.core.constants.StorageConstants;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "com.iccd.storage.server")
@Data
public class StorageServerConfig {


    private Integer chunkFileExpirationDays = StorageConstants.ONE_INT;

    private String sharePrefix = "http://127.0.0.1:8082/share/";


}
