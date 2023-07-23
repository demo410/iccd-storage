package com.iccd.storage.schedule;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.concurrent.ScheduledFuture;

/**
 * 定时任务和定时任务结果的缓存
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ScheduleTaskHolder implements Serializable {

    private ScheduleTask scheduleTask;

    private ScheduledFuture scheduledFuture;


}
