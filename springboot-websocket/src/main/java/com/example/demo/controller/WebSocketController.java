package com.example.demo.controller;


import com.example.demo.util.WebSocketServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ws")
public class WebSocketController {

    /**
     * 群发消息内容
     */
    @RequestMapping(value = "sendall", method = RequestMethod.GET)
    public String sendAllMessage(@RequestParam(required = true) String message) {
        WebSocketServer.BroadCastInfo(message);
        return "ok";
    }
}
