package com.github.shoothzj.heartbeat.core.task;

import com.github.shoothzj.heartbeat.core.module.ProtocolEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * @author hezhangjian
 */
public abstract class AbstractHeartBeatTask {

    @Setter
    @Getter
    private int successThreshold;

    @Setter
    @Getter
    private int failureThreshold;

    @Setter
    @Getter
    private int timeout;

    @Setter
    @Getter
    private int intervalDelay;

    public AbstractHeartBeatTask(int successThreshold, int failureThreshold, int timeout, int intervalDelay) {
        this.successThreshold = successThreshold;
        this.failureThreshold = failureThreshold;
        this.timeout = timeout;
        this.intervalDelay = intervalDelay;
    }

    /**
     * 获取task对应的协议枚举
     * @return
     */
    public abstract ProtocolEnum acquireProtocol();

}
