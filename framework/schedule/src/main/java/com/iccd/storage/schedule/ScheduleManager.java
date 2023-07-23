package com.iccd.storage.schedule;

import com.iccd.storage.core.exception.StorageFrameworkException;
import com.iccd.storage.core.utils.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

/**
 * 1. 创建并启动一个定时任务
 * 2. 停止一个定时任务
 * 3. 更新一个定时任务
 */
@Component
@Slf4j
public class ScheduleManager {

    @Autowired
    private ThreadPoolTaskScheduler taskScheduler;

    private Map<String, ScheduleTaskHolder> cache = new ConcurrentHashMap<>();


    public String startTask(ScheduleTask task, String cron){
        ScheduledFuture<?> scheduledFuture = taskScheduler.schedule(task, new CronTrigger(cron));
        ScheduleTaskHolder taskHolder = new ScheduleTaskHolder(task, scheduledFuture);
        String key = UUIDUtil.getUUID();

        cache.put(key, taskHolder);
        log.info("{} schedule task started, Key = {}", task.getName(), key);
        return key;
    }

    public void stopTask(String key){
        if (!cache.containsKey(key)) return ;

        ScheduleTaskHolder taskHolder = cache.get(key);

        ScheduledFuture scheduledFuture = taskHolder.getScheduledFuture();

        if (scheduledFuture.isDone() || scheduledFuture.isCancelled()) {
            return;
        }

        scheduledFuture.cancel(true);

    }

    /**
     * 更新定时任务的执行时间
     * @param key
     * @param cron
     * @return
     */
    public String changeTask(String key, String cron){
        if (StringUtils.isAnyBlank(key, cron)){
            throw new StorageFrameworkException("参数为空");
        }

        if (!cache.containsKey(key)){
            throw new StorageFrameworkException("定时任务不存在");
        }
        ScheduleTaskHolder taskHolder = cache.get(key);
        stopTask(key);

        return startTask(taskHolder.getScheduleTask(), cron);
    }
}
