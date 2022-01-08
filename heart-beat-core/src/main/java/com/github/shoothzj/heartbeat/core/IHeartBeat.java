package com.github.shoothzj.heartbeat.core;

import com.github.shoothzj.heartbeat.core.callback.IHeartBeatCallback;
import com.github.shoothzj.heartbeat.core.task.AbstractHeartBeatTask;

/**
 * @author hezhangjian
 */
public interface IHeartBeat {

    /**
     * 开始心跳探测业务
     * @param taskId
     * @param heartBeatTask
     * @param heartBeatCallback
     */
    void startTask(String taskId, AbstractHeartBeatTask heartBeatTask, IHeartBeatCallback heartBeatCallback);

    /**
     * 停止心跳探测业务
     * @param taskId
     */
    void stopTask(String taskId);

}
