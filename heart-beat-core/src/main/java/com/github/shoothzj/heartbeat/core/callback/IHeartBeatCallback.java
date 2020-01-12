package com.github.shoothzj.heartbeat.core.callback;

/**
 * @author hezhangjian
 */
public interface IHeartBeatCallback {

    void successLog();

    void failureLog();

    void becomeSuccess();

    void becomeFailure();

}
