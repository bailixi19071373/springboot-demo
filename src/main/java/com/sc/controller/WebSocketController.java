package com.sc.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/ws")
public class WebSocketController {


    @RequestMapping("/towebsocket")
    public String towebscoket() {
        return "websocket/ws-client1";
    }

}