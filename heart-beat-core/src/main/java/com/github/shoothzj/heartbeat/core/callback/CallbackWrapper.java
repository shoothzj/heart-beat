package com.github.shoothzj.heartbeat.core.callback;

import com.github.shoothzj.heartbeat.core.module.StateEnum;
import lombok.extern.slf4j.Slf4j;

/**
 * 用来包裹callback,免于每个callback都进行计数
 * 此wrapper可能会被多线程调用,但不会被同时调用
 * 所以volatile级别的并发控制就已经足够
 * @author hezhangjian
 */
@Slf4j
public class CallbackWrapper<T extends IHeartBeatCallback> {

    private final T callback;

    private final int successThreshold;

    private final int failureThreshold;

    private volatile int successCount = 0;

    private volatile int failureCount = 0;

    private volatile StateEnum currentState = StateEnum.UNKNOWN;

    public CallbackWrapper(T callback, int successThreshold, int failureThreshold) {
        this.callback = callback;
        this.successThreshold = successThreshold;
        this.failureThreshold = failureThreshold;
    }

    public void reportSuccess() {
        failureCount = 0;
        successCount++;
        if (successCount == successThreshold && !currentState.equals(StateEnum.AVAILABLE)) {
            callback.becomeSuccess();
            currentState = StateEnum.AVAILABLE;
        }
    }

    public void reportFailure() {
        successCount = 0;
        failureCount++;
        if (failureCount == failureThreshold && !currentState.equals(StateEnum.UNAVAILABLE)) {
            callback.becomeFailure();
            currentState = StateEnum.UNAVAILABLE;
        }
    }

}
