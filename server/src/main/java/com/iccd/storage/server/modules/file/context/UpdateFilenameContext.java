package com.iccd.storage.server.modules.file.context;

import com.iccd.storage.server.modules.file.entity.UserFile;
import lombok.Data;

import java.io.Serializable;

@Data
public class UpdateFilenameContext implements Serializable {

    private static final long serialVersionUID = -785955065061251673L;
    /**
     * 要更新的文件Id
     */
    private Long fileId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 新的文件名称
     */
    private String newFilename;

    /**
     * 更新的文件记录实体
     */
    private UserFile entity;
}
