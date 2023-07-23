package com.iccd.storage.server.modules.file.context;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class DeleteFileContext implements Serializable {
    private static final long serialVersionUID = -5040051387091567725L;

    /**
     * 要删除的文件ID集合
     */
    private List<Long> fileIdList;

    /**
     * 当前的登录用户ID
     */
    private Long userId;
}
