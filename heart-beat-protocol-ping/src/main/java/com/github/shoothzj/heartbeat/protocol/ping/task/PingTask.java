package com.github.shoothzj.heartbeat.protocol.ping.task;

import com.github.shoothzj.heartbeat.core.module.ProtocolEnum;
import com.github.shoothzj.heartbeat.core.task.AbstractHeartBeatTask;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author hezhangjian
 */
@Slf4j
public class PingTask extends AbstractHeartBeatTask {

    @Getter
    private String addr;

    public PingTask(int successThreshold, int failureThreshold, int timeout, int intervalDelay, String addr) {
        super(successThreshold, failureThreshold, timeout, intervalDelay);
        this.addr = addr;
    }


    @Override
    public ProtocolEnum acquireProtocol() {
        return ProtocolEnum.PING;
    }

}
