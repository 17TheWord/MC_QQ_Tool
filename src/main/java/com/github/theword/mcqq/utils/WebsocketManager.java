package com.github.theword.mcqq.utils;

import com.github.theword.mcqq.constant.CommandConstantMessage;
import com.github.theword.mcqq.constant.WebsocketConstantMessage;
import com.github.theword.mcqq.websocket.WsClient;
import com.github.theword.mcqq.websocket.WsServer;

import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.atomic.AtomicInteger;

import static com.github.theword.mcqq.utils.Tool.*;

public class WebsocketManager {
    /**
     * 启动 WebSocket 客户端
     *
     * @param commandReturner 命令执行者
     */
    public static void startWebsocketClients(Object commandReturner) {
        if (config.isEnableWebsocketClient())
            config.getWebsocketUrlList().forEach(websocketUrl -> {
                try {
                    WsClient wsClient = new WsClient(new URI(websocketUrl));
                    wsClient.connect();
                    wsClientList.add(wsClient);
                } catch (URISyntaxException e) {
                    commandReturn(commandReturner, "连接至 " + websocketUrl + " 的地址不合法");
                    logger.warn(String.format("[MC_QQ] 连接至 %s 的地址不合法", websocketUrl));
                }
            });
    }

    /**
     * 停止 WebSocket 客户端
     *
     * @param code   Code
     * @param reason 原因
     */
    public static void stopWebsocketClients(int code, String reason) {
        wsClientList.forEach(wsClient -> wsClient.stopWithoutReconnect(code, String.format(reason, wsClient.getURI())));
        wsClientList.clear();
    }

    /**
     * 重连 WebSocket 客户端
     *
     * @param all             是否全部重连
     * @param commandReturner 命令执行者
     */
    public static void reconnectWebsocketClients(boolean all, Object commandReturner) {
        String reconnectCount = all ? CommandConstantMessage.RECONNECT_ALL_CLIENT : CommandConstantMessage.RECONNECT_NOT_OPEN_CLIENT;
        commandReturn(commandReturner, reconnectCount);

        AtomicInteger opened = new AtomicInteger();
        wsClientList.forEach(wsClient -> {
            if (all || !wsClient.isOpen()) {
                wsClient.reconnectWebsocket();
                commandReturn(commandReturner, String.format(CommandConstantMessage.RECONNECT_MESSAGE, wsClient.getURI()));
            } else {
                opened.getAndIncrement();
            }
        });
        if (opened.get() == wsClientList.size()) {
            commandReturn(commandReturner, CommandConstantMessage.RECONNECT_NO_CLIENT_NEED_RECONNECT);
        }
        commandReturn(commandReturner, CommandConstantMessage.RECONNECTED);
    }

    /**
     * 重载 WebSocket 客户端
     *
     * @param commandReturner 命令执行者
     */
    public static void restartWebsocketClients(Object commandReturner) {
        stopWebsocketClients(1000, "[MC_QQ] 重载 Clients");
        startWebsocketClients(commandReturner);
    }

    /**
     * 启动 WebSocket 服务器
     */
    public static void startWebsocketServer() {
        if (config.isEnableWebsocketServer()) {
            wsServer = new WsServer(new InetSocketAddress(config.getWebsocketServerHost(), config.getWebsocketServerPort()));
            wsServer.start();
        }
    }

    /**
     * 停止 WebSocket 服务器
     */
    public static void stopWebsocketServer() {
        if (wsServer != null) {
            try {
                wsServer.stop();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            wsServer = null;
        }
    }

    /**
     * 重载 WebSocket 服务器
     */
    public static void restartWebsocketServer() {
        stopWebsocketServer();
        startWebsocketServer();
    }

    /**
     * 启动 WebSocket
     *
     * @param commandReturner 命令执行者
     */
    public static void startWebsocket(Object commandReturner) {
        logger.info(WebsocketConstantMessage.WEBSOCKET_RUNNING);
        startWebsocketClients(commandReturner);
        startWebsocketServer();
    }

    /**
     * 停止 WebSocket
     *
     * @param code   Code
     * @param reason 原因
     */
    public static void stopWebsocket(int code, String reason) {
        stopWebsocketClients(code, reason);
        stopWebsocketServer();
    }


    /**
     * 重载 WebSocket
     *
     * @param isModServer     是否为 ModServer
     * @param commandReturner 命令执行者
     */
    public static void reloadWebsocket(boolean isModServer, Object commandReturner) {
        config = new Config(isModServer);
        stopWebsocket(1000, "[MC_QQ] 重载 Websocket");
        startWebsocket(commandReturner);
    }
}
