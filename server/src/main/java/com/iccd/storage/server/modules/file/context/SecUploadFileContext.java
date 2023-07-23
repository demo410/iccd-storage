package com.iccd.storage.server.modules.file.context;

import lombok.Data;

import java.io.Serializable;

@Data
public class SecUploadFileContext implements Serializable {
    private static final long serialVersionUID = 865765374680289146L;

    /**
     * 文件的父ID
     */
    private Long parentId;

    /**
     * 文件名称
     */
    private String filename;

    /**
     * 文件的唯一标识
     */
    private String identifier;

    /**
     * 当前登录用的ID
     */
    private Long userId;
}
