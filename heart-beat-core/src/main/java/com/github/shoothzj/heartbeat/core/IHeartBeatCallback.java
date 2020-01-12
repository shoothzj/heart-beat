package com.github.shoothzj.heartbeat.core;

/**
 * @author hezhangjian
 */
public interface IHeartBeatCallback {

    void successLog();

    void failureLog();

    void becomeSuccess();

    void becomeFailure();

}
