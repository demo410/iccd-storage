package com.iccd.storage.server.common.listener;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.iccd.storage.core.utils.IdUtil;
import com.iccd.storage.server.common.event.search.UserSearchEvent;
import com.iccd.storage.server.modules.user.entity.UserSearchHistory;
import com.iccd.storage.server.modules.user.service.IUserSearchHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class UserSearchEventListener {

    @Autowired
    private IUserSearchHistoryService iUserSearchHistoryService;

    /**
     * 保存用户搜索的历史
     * @param event
     */
    @EventListener(UserSearchEvent.class)
    public void saveSearchHistory(UserSearchEvent event){
        UserSearchHistory entity = new UserSearchHistory();
        entity.setSearchContent(event.getKeyword());
        entity.setUserId(event.getUserId());
        entity.setCreateTime(new Date());
        entity.setId(IdUtil.get());
        entity.setUpdateTime(entity.getUpdateTime());

        try {
            iUserSearchHistoryService.save(entity);
        }catch (DuplicateKeyException e){
            UpdateWrapper wrapper = new UpdateWrapper();
            wrapper.eq("user_id", event.getUserId());
            wrapper.eq("search_content", event.getKeyword());
            wrapper.set("update_time", new Date());
            iUserSearchHistoryService.update(wrapper);
        }
    }
}
