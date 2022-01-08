package com.github.shoothzj.heartbeat.protocol.ping.task;

import com.github.shoothzj.heartbeat.core.callback.CallbackWrapper;
import com.github.shoothzj.heartbeat.protocol.ping.callback.IPingCallback;
import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;

/**
 * @author hezhangjian
 */
@Slf4j
public class PingWorkTask implements Runnable {

    private final PingTask pingTask;

    private final IPingCallback pingCallback;

    private final CallbackWrapper<IPingCallback> callbackWrapper;

    public PingWorkTask(PingTask pingTask, IPingCallback pingCallback) {
        this.pingTask = pingTask;
        this.pingCallback = pingCallback;
        this.callbackWrapper = new CallbackWrapper<>(pingCallback, pingTask.getSuccessThreshold(),
                pingTask.getFailureThreshold());
    }

    @Override
    public void run() {
        boolean result = false;
        try {
            InetAddress inetAddress = InetAddress.getByName(pingTask.getAddr());
            result = inetAddress.isReachable(pingTask.getTimeout());
        } catch (Exception e) {
            log.error("inet address ping exception ", e);
        }
        if (result) {
            callbackWrapper.reportSuccess();
        } else {
            callbackWrapper.reportFailure();
        }
    }

}
