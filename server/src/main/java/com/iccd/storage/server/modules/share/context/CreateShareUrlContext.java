package com.iccd.storage.server.modules.share.context;

import com.iccd.storage.server.modules.share.entity.Share;
import lombok.Data;

import java.util.List;

@Data
public class CreateShareUrlContext {
    private static final long serialVersionUID = 2945253863400727173L;

    /**
     * 分享的名称
     */
    private String shareName;

    /**
     * 分享的类型
     */
    private Integer shareType;

    /**
     * 分享的日期类型
     */
    private Integer shareDayType;

    /**
     * 该分项对应的文件ID集合
     */
    private List<Long> shareFileIdList;

    /**
     * 当前登录的用户ID
     */
    private Long userId;

    /**
     * 已经保存的分享实体信息
     */
    private Share record;
}
