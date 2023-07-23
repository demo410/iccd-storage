package com.iccd.storage.lock.zookeeper;

import com.iccd.storage.lock.core.LockConstants;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "com.iccd.storage.lock.zookeeper")
public class ZooKeeperLockProperties {

    /**
     * zk链接地址，多个使用逗号隔开
     */
    private String host = "127.0.0.1:2181";

    /**
     * zk分布式锁的根路径
     */
    private String rootPath = LockConstants.ICCD_STORAGE_LOCK_PATH;

}
