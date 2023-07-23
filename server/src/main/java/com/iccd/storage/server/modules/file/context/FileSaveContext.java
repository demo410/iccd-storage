package com.iccd.storage.server.modules.file.context;

import com.iccd.storage.server.modules.file.entity.IccdFile;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * 单文件上传的上下文实体
 */
@Data
public class FileSaveContext implements Serializable {


    private static final long serialVersionUID = -2801522653527861746L;
    /**
     * 文件名称
     */
    private String filename;

    /**
     * 文件唯一标识
     */
    private String identifier;

    /**
     * 文件大小
     */
    private Long totalSize;


    /**
     * 要上传的文件实体
     */
    private MultipartFile file;

    /**
     * 当前登录的用户ID
     */
    private Long userId;

    /**
     * 实体文件记录
     */
    private IccdFile record;

    /**
     * 文件上传的路径
     */
    private String realPath;

}
