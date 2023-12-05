package com.wesley.controller;


import com.wesley.pojo.Admin;
import com.wesley.pojo.ChatMsg;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
public class WsController {
    private SimpMessagingTemplate simpMessagingTemplate;

    public void handMsg(Authentication authentication, ChatMsg chatMsg){
        Admin admin = (Admin) authentication.getPrincipal();
        chatMsg.setFrom(admin.getUsername());
        chatMsg.setFromNickName(admin.getName());
        chatMsg.setDate(LocalDateTime.now());
        simpMessagingTemplate.convertAndSendToUser(chatMsg.getTo(), "/queue/chat", chatMsg);
    }
}
