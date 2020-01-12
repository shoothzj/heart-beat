package com.github.shoothzj.heartbeat.core;

import com.github.shoothzj.heartbeat.core.callback.IHeartBeatCallback;
import com.github.shoothzj.heartbeat.core.common.AbstractHeartBeatImpl;
import com.github.shoothzj.heartbeat.core.module.ProtocolEnum;
import com.github.shoothzj.heartbeat.core.task.AbstractHeartBeatTask;
import com.github.shoothzj.heartbeat.core.util.ReflectionUtil;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author hezhangjian
 */
@Slf4j
public class HeartBeatHelper implements IHeartBeat {

    private final Map<ProtocolEnum, AbstractHeartBeatImpl> map = new ConcurrentHashMap<>();

    private final Map<String, ProtocolEnum> taskProtocolMap = new ConcurrentHashMap<>();

    @Override
    public void startTask(String taskId, AbstractHeartBeatTask heartBeatTask, IHeartBeatCallback heartBeatCallback) {
        ProtocolEnum protocol = heartBeatTask.acquireProtocol();
        Class<?> clazz;
        try {
            clazz = Class.forName(protocol.className);
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(String.format("dependency class %s not found", protocol.className));
        }
        AbstractHeartBeatImpl heartBeatImpl = getHeartBeatImpl(clazz, protocol);
        taskProtocolMap.put(taskId, protocol);
        ReflectionUtil.invokeStartTask(clazz, heartBeatImpl, taskId, heartBeatTask, heartBeatCallback);
    }

    @Override
    public void stopTask(String taskId) {
        ProtocolEnum protocol = taskProtocolMap.get(taskId);
        Class<?> clazz;
        try {
            clazz = Class.forName(protocol.className);
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(String.format("dependency class %s not found", protocol.className));
        }
        ReflectionUtil.invokeStopTask(clazz, getHeartBeatImpl(clazz, protocol), taskId);
    }

    private AbstractHeartBeatImpl getHeartBeatImpl(Class<?> clazz, ProtocolEnum protocol) {
        map.computeIfAbsent(protocol, protocolEnum -> {
            try {
                Constructor<?> constructor = clazz.getConstructor(Properties.class);
                Properties properties = new Properties();
                //使用properties作为构造函数,方便后续扩展
                return (AbstractHeartBeatImpl) constructor.newInstance(properties);
            } catch (Exception e) {
                log.error("construct heartbeat impl failed ", e);
            }
            return null;
        });
        return map.get(protocol);
    }

}
