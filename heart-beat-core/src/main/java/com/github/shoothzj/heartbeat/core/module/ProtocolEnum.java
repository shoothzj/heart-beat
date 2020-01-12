package com.github.shoothzj.heartbeat.core.module;

/**
 * @author hezhangjian
 */
public enum ProtocolEnum {

    /**
     * 使用ping命令进行探测
     */
    PING("");

    public final String className;

    ProtocolEnum(String className) {
        this.className = className;
    }

}
