package com.iccd.storage.server.modules.recycle.context;

import com.iccd.storage.server.modules.file.entity.UserFile;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class DeleteContext implements Serializable {

    private static final long serialVersionUID = -588491390915976064L;

    /**
     * 要操作的文件ID的集合
     */
    private List<Long> fileIdList;

    /**
     * 当前登录的用户ID
     */
    private Long userId;

    /**
     * 要被删除的文件记录列表
     */
    private List<UserFile> records;

    /**
     * 所有要被删除的文件记录列表
     */
    private List<UserFile> allRecords;

}
