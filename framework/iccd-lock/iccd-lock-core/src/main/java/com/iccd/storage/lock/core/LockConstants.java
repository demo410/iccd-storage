package com.iccd.storage.lock.core;

/**
 * 锁相关公用常量类
 */
public interface LockConstants {

    /**
     * 公用lock的名称
     */
    String ICCD_STORAGE_LOCK = "iccd-storage-lock;";

    /**
     * 公用lock的path
     * 主要针对zk等节点型软件
     */
    String ICCD_STORAGE_LOCK_PATH = "/iccd-storage-lock";

}
