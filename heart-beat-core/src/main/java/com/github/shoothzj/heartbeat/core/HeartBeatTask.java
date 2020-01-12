package com.github.shoothzj.heartbeat.core;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author hezhangjian
 */
@Slf4j
@Data
public class HeartBeatTask {

    private Protocol protocol;

    private int successThreshold;

}
