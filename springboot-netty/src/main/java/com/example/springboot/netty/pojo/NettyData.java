package com.example.springboot.netty.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @create 2022-01-15 20:17
 */
@Component
//@ConfigurationProperties(prefix = "netty")
public class NettyData {
    @Value("${netty.websocket.port}")
    private int port;
    @Value("${netty.websocket.ip}")
    private String ip;
    @Value("${netty.websocket.path}")
    private String path;
    @Value("${netty.websocket.max-frame-size}")
    private long maxFrameSize;


    public int getPort() {
        return port;
    }


    public String getIp() {
        return ip;
    }

    public String getPath(){
        return path;
    }
    public long getMaxFrameSize(){
        return maxFrameSize;
    }

    public NettyData() {
    }

    public NettyData(int port, String ip, String path, long maxFrameSize) {
        this.port = port;
        this.ip = ip;
        this.path = path;
        this.maxFrameSize = maxFrameSize;
    }

}
