package com.iccd.storage.schedule;

public interface ScheduleTask extends Runnable{

    /**
     * 获取定时任务的名称
     * @return
     */
    String getName();
}
