package com.github.shoothzj.heartbeat.core;

import com.github.shoothzj.javatool.executor.SimpleLogRejectedExecutionHandler;
import io.netty.util.concurrent.DefaultThreadFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @author hezhangjian
 */
@Slf4j
public class HeartBeatWorker {

    private static final int CORE_SIZE = 5;

    private static final ScheduledThreadPoolExecutor scheduleService =
            new ScheduledThreadPoolExecutor(CORE_SIZE,
                    new DefaultThreadFactory("heart-worker"),
                    new SimpleLogRejectedExecutionHandler());

    static {
        scheduleService.setRemoveOnCancelPolicy(true);
    }

    public static ScheduledThreadPoolExecutor getScheduleService() {
        return scheduleService;
    }
}
