package com.example.springboot.netty.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @create 2022-01-15 22:26
 */
@Service
public class DiscardService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DiscardService.class);
    //消息
    public void discard(String message) {
        LOGGER.info("丢弃消息:{}", message);
    }
}
