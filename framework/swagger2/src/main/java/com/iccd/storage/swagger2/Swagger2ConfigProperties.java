package com.iccd.storage.swagger2;

import com.iccd.storage.core.constants.StorageConstants;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "swagger2")
public class Swagger2ConfigProperties {
    private boolean show = true;

    private String groupName = "iccd-storage";

    private String basePackage = StorageConstants.BASE_COMPONENT_SCAN_PATH;

    private String title = "iccd-storage-server";

    private String description = "Private storage for iccd in DLUT";

    private String termsOfServiceUrl = "http://127.0.0.1:${server.port}";

    private String version = "2.0";

}
