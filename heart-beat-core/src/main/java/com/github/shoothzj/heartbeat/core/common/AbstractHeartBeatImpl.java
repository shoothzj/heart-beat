package com.github.shoothzj.heartbeat.core.common;

import com.github.shoothzj.heartbeat.core.HeartBeatWorker;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author hezhangjian
 */
@Slf4j
public abstract class AbstractHeartBeatImpl {

    private Map<String, ScheduledFuture<?>> map = new ConcurrentHashMap<>();

    protected Properties properties;

    public AbstractHeartBeatImpl(Properties properties) {
        this.properties = properties;
    }

    protected void addTaskHelp(String taskId, Runnable runnable, int intervalDelay) {
        ScheduledFuture<?> scheduledFuture = HeartBeatWorker.getScheduleService()
                .scheduleWithFixedDelay(runnable, 0, intervalDelay, TimeUnit.MILLISECONDS);
        map.put(taskId, scheduledFuture);
    }

    protected void stopTask(String taskId) {
        map.computeIfPresent(taskId, (s, scheduledFuture) -> {
            scheduledFuture.cancel(false);
            return null;
        });
    }

}
