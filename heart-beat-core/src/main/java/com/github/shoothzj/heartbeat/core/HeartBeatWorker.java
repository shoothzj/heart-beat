package com.github.shoothzj.heartbeat.core;

import com.github.shoothzj.javatool.executor.SimpleLogRejectedExecutionHandler;
import io.netty.util.concurrent.DefaultThreadFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author hezhangjian
 */
@Slf4j
public class HeartBeatWorker {

    private static final int coreSize = 5;

    private static final int maxSize = 200;

    private static final int keepAliveTime = 5;

    private static final ExecutorService executorService = new ThreadPoolExecutor(coreSize, maxSize, keepAliveTime, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(1024), new DefaultThreadFactory("heart-worker"),
            new SimpleLogRejectedExecutionHandler());

    private static final ScheduledThreadPoolExecutor scheduleService = new ScheduledThreadPoolExecutor(coreSize, new DefaultThreadFactory("heart-worker"), new RejectedExecutionHandler() {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {

        }
    });

    static {
        scheduleService.setRemoveOnCancelPolicy(true);
    }

}
