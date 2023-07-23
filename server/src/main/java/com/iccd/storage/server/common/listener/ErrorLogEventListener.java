package com.iccd.storage.server.common.listener;

import com.iccd.storage.core.utils.IdUtil;
import com.iccd.storage.server.common.event.log.ErrorLogEvent;
import com.iccd.storage.server.modules.log.entity.ErrorLog;
import com.iccd.storage.server.modules.log.service.IErrorLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ErrorLogEventListener {

    @Autowired
    private IErrorLogService iErrorLogService;

    /**
     * 监听系统错误日志事件，并保存到数据库中
     * @param event
     */
    @EventListener(ErrorLogEvent.class)
    public void saveErrorLog(ErrorLogEvent event){
        ErrorLog entity = new ErrorLog();

        entity.setId(IdUtil.get());
        entity.setLogContent(event.getMsg());
        entity.setCreateUser(event.getUserId());
        entity.setUpdateUser(event.getUserId());
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
        entity.setLogStatus(0);

        iErrorLogService.save(entity);
    }
}
