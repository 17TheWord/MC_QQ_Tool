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
    public void startWebsocketClients(Object commandReturner) {
        if (config.isEnableWebsocketClient()) {
            logger.info(WebsocketConstantMessage.Client.LAUNCHING);
            config.getWebsocketUrlList().forEach(websocketUrl -> {
                try {
                    WsClient wsClient = new WsClient(new URI(websocketUrl));
                    wsClient.connect();
                    wsClientList.add(wsClient);
                } catch (URISyntaxException e) {
                    commandReturn(commandReturner, String.format(WebsocketConstantMessage.Client.ERROR_URI_SYNTAX_ERROR, websocketUrl));
                    logger.warn(String.format(WebsocketConstantMessage.Client.ERROR_URI_SYNTAX_ERROR, websocketUrl));
                }
            });
        }
    }

    /**
     * 停止 WebSocket 客户端
     *
     * @param code   Code
     * @param reason 原因
     */
    public void stopWebsocketClients(int code, String reason, Object commandReturner) {
        wsClientList.forEach(wsClient -> {
            commandReturn(commandReturner, String.format(WebsocketConstantMessage.Client.CLOSING, wsClient.getURI()));
            wsClient.stopWithoutReconnect(code, String.format(reason, wsClient.getURI()));
        });
        wsClientList.clear();
        commandReturn(commandReturner, WebsocketConstantMessage.CLOSE_BY_RELOAD);
    }

    /**
     * 重连 WebSocket 客户端
     *
     * @param all             是否全部重连
     * @param commandReturner 命令执行者
     */
    public void reconnectWebsocketClients(boolean all, Object commandReturner) {
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
    public void restartWebsocketClients(Object commandReturner) {
        stopWebsocketClients(1000, WebsocketConstantMessage.CLOSE_BY_RELOAD, commandReturner);
        startWebsocketClients(commandReturner);
    }

    /**
     * 启动 WebSocket 服务器
     */
    public void startWebsocketServer() {
        if (config.isEnableWebsocketServer()) {
            wsServer = new WsServer(new InetSocketAddress(config.getWebsocketServerHost(), config.getWebsocketServerPort()));
            wsServer.start();
        }
    }

    /**
     * 停止 WebSocket 服务器
     *
     * @param commandReturner 命令执行者
     */
    public void stopWebsocketServer(Object commandReturner) {
        if (wsServer != null) {
            try {
                wsServer.stop();
                commandReturn(commandReturner, WebsocketConstantMessage.Server.CLOSING);
            } catch (InterruptedException e) {
                commandReturn(commandReturner, String.format(WebsocketConstantMessage.Server.CLOSING_ERROR, e.getMessage()));
                throw new RuntimeException(e);
            }
            wsServer = null;
        }
    }

    /**
     * 重载 WebSocket 服务器
     *
     * @param commandReturner 命令执行者
     */
    public void restartWebsocketServer(Object commandReturner) {
        stopWebsocketServer(commandReturner);
        startWebsocketServer();
    }

    /**
     * 启动 WebSocket
     *
     * @param commandReturner 命令执行者
     */
    public void startWebsocket(Object commandReturner) {
        startWebsocketClients(commandReturner);
        startWebsocketServer();
    }

    /**
     * 停止 WebSocket
     *
     * @param code            Code
     * @param reason          原因
     * @param commandReturner 命令执行者
     */
    public void stopWebsocket(int code, String reason, Object commandReturner) {
        stopWebsocketClients(code, reason, commandReturner);
        stopWebsocketServer(commandReturner);
    }


    /**
     * 重载 WebSocket
     *
     * @param isModServer     是否为 ModServer
     * @param commandReturner 命令执行者
     */
    public void reloadWebsocket(boolean isModServer, Object commandReturner) {
        config = new Config(isModServer);
        commandReturn(commandReturner, CommandConstantMessage.RELOAD_CONFIG);
        stopWebsocket(1000, WebsocketConstantMessage.CLOSE_BY_RELOAD, commandReturner);
        startWebsocket(commandReturner);
    }
}
