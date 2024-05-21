package com.github.theword.mcqq.websocket;

import com.github.theword.mcqq.constant.WebsocketConstantMessage;
import com.github.theword.mcqq.handleMessage.HandleProtocolMessage;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;

import static com.github.theword.mcqq.utils.Tool.*;

public class WsServer extends WebSocketServer {
    private final HandleProtocolMessage handleProtocolMessage = new HandleProtocolMessage();

    public WsServer(InetSocketAddress address) {
        super(address);
    }

    @Override
    public void onOpen(WebSocket webSocket, ClientHandshake clientHandshake) {
        String originServerName = clientHandshake.getFieldValue("x-self-name");
        if (originServerName.isEmpty()) {
            logger.warn(String.format("来自 %s 的连接请求头中缺少服务器名，将断开连接", webSocket.getRemoteSocketAddress().getHostString()));
            webSocket.close(1008, "Missing X-Self-name Header");
            return;
        }

        String clientOrigin = clientHandshake.getFieldValue("x-client-origin");

        if (clientOrigin.equalsIgnoreCase("minecraft")) {
            logger.warn(String.format("来自 %s 的连接请求头中客户端来源为 minecraft，将断开连接", webSocket.getRemoteSocketAddress().getHostString()));
            webSocket.close(1008, "X-Client-Origin Header cannot be minecraft");
            return;
        }

        String serverName = unicodeDecode(originServerName);
        if (serverName.isEmpty()) {
            logger.warn(String.format("来自 %s 的连接请求头中服务器名解析失败，将断开连接", webSocket.getRemoteSocketAddress().getHostString()));
            webSocket.close(1008, "X-Self-name Header cannot be empty");
            return;
        }
        logger.info(String.format("来自 %s 的连接已建立", webSocket.getRemoteSocketAddress().getHostString()));
    }

    @Override
    public void onClose(WebSocket webSocket, int code, String reason, boolean remote) {
        if (remote) {
            logger.info(String.format("来自 %s 的连接已关闭", webSocket.getRemoteSocketAddress().getHostString()));
        } else {
            logger.info(String.format("已关闭来自 %s 的连接", webSocket.getRemoteSocketAddress().getHostString()));
        }
    }

    @Override
    public void onMessage(WebSocket webSocket, String message) {
        if (config.isEnableMcQQ()) {
            try {
                handleWebsocketMessage.handleWebSocketJson(message);
            } catch (Exception e) {
                logger.warn(String.format(WebsocketConstantMessage.WEBSOCKET_ERROR_ON_MESSAGE, webSocket.getRemoteSocketAddress()));
                logger.warn(e.getMessage());
            }
        }
    }

    @Override
    public void onError(WebSocket webSocket, Exception exception) {
        logger.warn(String.format(WebsocketConstantMessage.WEBSOCKET_ON_ERROR, webSocket.getRemoteSocketAddress().toString().replaceFirst("/", ""), exception.getMessage()));
    }

    @Override
    public void onStart() {
        logger.info("Websocket Server 正在启动");
    }
}
