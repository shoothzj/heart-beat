package com.github.shoothzj.heartbeat.core;

/**
 * @author hezhangjian
 */
public interface IHeartBeat {

    /**
     * 开始心跳探测业务
     * @param heartBeatTask
     * @param heartBeatCallback
     * @return
     */
    int startHeartBeatTask(HeartBeatTask heartBeatTask, IHeartBeatCallback heartBeatCallback);

}
