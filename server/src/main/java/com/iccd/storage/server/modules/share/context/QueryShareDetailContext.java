package com.iccd.storage.server.modules.share.context;

import com.iccd.storage.server.modules.share.entity.Share;
import com.iccd.storage.server.modules.share.vo.ShareDetailVO;
import lombok.Data;

import java.io.Serializable;

/**
 * 查询分享详情的上下文实体对象
 */
@Data
public class QueryShareDetailContext implements Serializable {

    /**
     * 对应的分享ID
     */
    private Long shareId;

    /**
     * 分享实体
     */
    private Share record;

    /**
     * 分享详情的VO对象
     */
    private ShareDetailVO vo;

}
