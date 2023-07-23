package com.iccd.storage.server.modules.file.context;

import lombok.Data;

import java.io.Serializable;

@Data
public class CreateFolderContext implements Serializable {

    private static final long serialVersionUID = -861882709652125971L;

    /**
     * 父文件夹ID
     */
    private Long parentId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 文件夹名称
     */
    private String folderName;

}
