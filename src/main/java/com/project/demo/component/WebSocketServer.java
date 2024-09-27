package com.project.demo.component;

import com.project.demo.service.UserService;
import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@ServerEndpoint(value = "/api/webSocket/{id}")
@Component
@Slf4j
@Data
public class WebSocketServer {
    private Session session;
    private String id;

    private UserService userService;

    @OnOpen
    public void onOpen(Session session, @PathParam("id") String id) {
        this.session = session;
        this.id = id;
        this.userService = SpringContextHolder.getBean(UserService.class);
        userService.changeUserStatus(Integer.parseInt(id), true);
        log.info("连接接入，userid:{}, session:{}", id, session.getId());
        System.out.println("连接接入，userId:" + id + "   session:" + session.getId());
    }

    @OnClose
    public void onClose() {
        this.userService = SpringContextHolder.getBean(UserService.class);
        userService.changeUserStatus(Integer.parseInt(id), false);
        log.info("连接断开，userid:{}, session:{}", id, session.getId());
        System.out.println("连接断开，userId:" + id + "   session:" + session.getId());
    }
}
