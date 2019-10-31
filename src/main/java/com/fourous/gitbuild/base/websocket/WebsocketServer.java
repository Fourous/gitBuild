package main.java.com.fourous.gitbuild.base.websocket;

import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author fourous
 * @date: 2019/10/31
 * @description: Websocket服务端
 */
@ServerEndpoint("websocket")
public class WebsocketServer {
    private static final Logger logger = LoggerFactory.getLogger(WebsocketServer.class);
    private static Map<String, Session> sessionMap = new HashedMap();

    @OnOpen
    public void oneOpen(Session session) throws IOException {
        sessionMap.put(session.getId(), session);
        HashMap<String, String> message = new HashMap<>();
        message.put("id", session.getId());
        message.put("type", "register");

    }

    @OnClose
    public void onClose() {

    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {

    }

    @OnError
    public void onError(Session session, Throwable error) {

    }

    private void SendMessage(String message, Session session) throws IOException {
        sendText(session, message);
    }

    private static synchronized void sendText(Session session, String message) throws IOException {
        session.getBasicRemote().sendText(message);
    }

    public static void sendMessage(String message, String missionId) throws IOException {
        Session session = sessionMap.get(missionId);
        if (session != null) {
            sendText(session, message);
        }
    }
}