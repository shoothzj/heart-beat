package com.github.shoothzj.heartbeat.ping.exec;

import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;

/**
 * @author hezhangjian
 */
@Slf4j
public class PingTestMain {

    public static void main(String[] args) throws Exception {
        String testIp = System.getProperty("TestIp");
        InetAddress inetAddress = InetAddress.getByName(testIp);
        boolean addressReachable = inetAddress.isReachable(500);
        log.info("address is reachable is {}", addressReachable);
    }

}
