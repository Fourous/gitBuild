package main.java.com.fourous.gitbuild.base.log;

import main.java.com.fourous.gitbuild.base.websocket.WebsocketServer;
import main.java.com.fourous.gitbuild.util.JsonUtil;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author fourous
 * @date: 2019/10/31
 * @description: 日志记录器
 */
public class Logger {
    public static Map<String, org.slf4j.Logger> loggers = new HashMap<>();

    /**
     * 推送信息到前端
     */
    public static void push(String sessionId, String meesage) {
        try {
            HashMap<String, String> messageMap = new HashMap<>();
            messageMap.put("type", "log");
            messageMap.put("message", meesage);
            WebsocketServer.sendMessage(JsonUtil.getMapToJson(messageMap), sessionId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 记录日志信息
     */
    public static void info(Class clazz, String message) {
        org.slf4j.Logger logger = getLogger(clazz);
        logger.info(message);
    }

    public static void info(String message) {
        org.slf4j.Logger logger = getLogger(Logger.class);
        logger.info(message);
    }

    /**
     * 记录日志信息并推送前端
     */
    public static void info(Class clazz, String sessionId, String message) {
        info(clazz, message);
        push(sessionId, message);
    }

    /**
     * 记录日志信息
     */
    public static void info(String sessonId, String message) {
        info(Logger.class, sessonId, message);
    }

    /**
     * 记录日志信息
     */
    public static void error(String message) {
        error(Logger.class, null, message);
    }

    /**
     * 记录日志信息并推送前端
     */
    public static void error(String sessionId, String message) {
        error(Logger.class, sessionId, message);
    }

    /**
     * 记录日志信息推送前端
     */
    public static void error(Class clazz, String sessionId, String message) {
        org.slf4j.Logger logger = getLogger(clazz);
        logger.error(message);
        if (sessionId != null) {
            push(sessionId, message);
        }
    }

    /**
     * 获取日志对象
     */
    public static org.slf4j.Logger getLogger(Class clazz) {
        org.slf4j.Logger logger = loggers.get(clazz.getName());
        if (logger == null) {
            logger = LoggerFactory.getLogger(clazz);
            loggers.put(clazz.getName(), logger);
        }
        return logger;
    }
}
