package com.sc.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

@Slf4j
@ServerEndpoint(value = "/websocket")
@Component
public class MyWebSocket {

    //concurrent包的线程安全Map，用来存放每个客户端对应的Websocket-Session对象。
    private static ConcurrentHashMap<String, Session> sessionMap = new ConcurrentHashMap<>();

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session) {
        sessionMap.put(session.getId(), session);     //加入map中
        log.debug("有新连接加入！当前在线人数为" + sessionMap.size());
        log.debug("websocket-sessionId=" + session.getId());

        while (true) {
            try {
                Date d = new Date();
                SimpleDateFormat f = new SimpleDateFormat("hh:mm:ss");
                sendInfo("当前在线人数为" + sessionMap.size() + ",当前系统时间是" + f.format(d));
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println("异常");
            }
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        sessionMap.remove(this);  //从set中删除
        System.out.println("有一连接关闭！当前在线人数为" + sessionMap.size());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自客户端的消息:" + message);

//        //群发消息
//        for (MyWebSocket item : sessionMap) {
//            try {
//                item.sendMessage(message);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
    }

    /**
     * 发生错误时调用
     *
     * @OnError
     */
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }


    /**
     * 发送消息
     *
     * @param message
     * @throws IOException
     */
    public void sendMessage(String sessionId, String message) throws IOException {
        sessionMap.get(sessionId).getBasicRemote().sendText(message);
        //this.session.getAsyncRemote().sendText(message);
    }


    /**
     * 群发自定义消息
     */
    public static void sendInfo(String message) throws IOException {
        for (Session session : sessionMap.values()) {
            try {
                session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                continue;
            }
        }
    }
}