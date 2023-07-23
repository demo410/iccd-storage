package com.iccd.storage.server.modules.recycle.service;

import com.iccd.storage.server.modules.file.vo.UserFileVO;
import com.iccd.storage.server.modules.recycle.context.DeleteContext;
import com.iccd.storage.server.modules.recycle.context.QueryRecycleFileListContext;
import com.iccd.storage.server.modules.recycle.context.RestoreContext;

import java.util.List;

public interface IRecycleService {

    /**
     * 查询用户回收站列表
     * @param context
     * @return
     */
    List<UserFileVO> recycles(QueryRecycleFileListContext context);

    void restore(RestoreContext context);

    void delete(DeleteContext context);
}
