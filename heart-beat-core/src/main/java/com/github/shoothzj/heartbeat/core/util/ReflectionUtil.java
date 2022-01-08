package com.github.shoothzj.heartbeat.core.util;

import com.github.shoothzj.heartbeat.core.common.AbstractHeartBeatImpl;
import com.github.shoothzj.heartbeat.core.constant.HeartBeatConstant;
import com.github.shoothzj.heartbeat.core.task.AbstractHeartBeatTask;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

/**
 * @author hezhangjian
 */
@Slf4j
public class ReflectionUtil {

    public static void invokeStartTask(Class<?> clazz, AbstractHeartBeatImpl target, String taskId,
                                       AbstractHeartBeatTask task, Object callback) {
        Method taskMethod = getTaskMethod(clazz, HeartBeatConstant.START_TASK_METHOD);
        try {
            taskMethod.invoke(target, taskId, task, callback);
        } catch (Exception ex) {
            throw new IllegalStateException("invoke method failed");
        }
    }

    public static void invokeStopTask(Class<?> clazz, AbstractHeartBeatImpl target, String taskId) {
        Method taskMethod = getTaskMethod(clazz, HeartBeatConstant.STOP_TASK_METHOD);
        try {
            taskMethod.invoke(target, taskId);
        } catch (Exception ex) {
            throw new IllegalStateException("invoke method failed");
        }
    }

    private static Method getTaskMethod(Class<?> clazz, String methodName) {
        Class<?> searchType = clazz;
        Method taskMethod = null;
        while (searchType != null) {
            Method[] methods = getDeclaredMethods(searchType);
            for (Method method : methods) {
                if (method.getName().equals(methodName)) {
                    taskMethod = method;
                }
            }
            searchType = searchType.getSuperclass();
        }
        if (taskMethod == null) {
            throw new IllegalStateException("impl is valid, can't find method");
        }
        return taskMethod;
    }

    private static Method[] getDeclaredMethods(Class<?> clazz) {
        Method[] result;
        try {
            result = clazz.getDeclaredMethods();
        } catch (Throwable ex) {
            throw new IllegalStateException("Failed to introspect Class [" + clazz.getName()
                    + "] from ClassLoader [" + clazz.getClassLoader() + "]", ex);
        }
        return result;
    }

}
