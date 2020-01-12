package com.github.shoothzj.heartbeat.protocol.ping;

import com.github.shoothzj.heartbeat.core.common.AbstractHeartBeatImpl;
import com.github.shoothzj.heartbeat.protocol.ping.callback.IPingCallback;
import com.github.shoothzj.heartbeat.protocol.ping.task.PingTask;
import com.github.shoothzj.heartbeat.protocol.ping.task.PingWorkTask;
import lombok.extern.slf4j.Slf4j;

import java.util.Properties;

/**
 * @author hezhangjian
 */
@Slf4j
public class PingHeartBeatImpl extends AbstractHeartBeatImpl {

    public PingHeartBeatImpl(Properties properties) {
        super(properties);
    }

    public void startTask(String taskId, PingTask pingTask, IPingCallback pingCallback) {
        this.addTaskHelp(taskId, new PingWorkTask(pingTask, pingCallback), pingTask.getIntervalDelay());
    }

}
