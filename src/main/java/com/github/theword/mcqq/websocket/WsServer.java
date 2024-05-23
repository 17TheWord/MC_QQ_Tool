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
            logger.warn(String.format(WebsocketConstantMessage.Server.MISSING_SERVER_NAME_IN_HEADER, webSocket.getRemoteSocketAddress().getHostString()));
            webSocket.close(1008, "Missing X-Self-name Header");
            return;
        }

        String clientOrigin = clientHandshake.getFieldValue("x-client-origin");

        if (clientOrigin.equalsIgnoreCase("minecraft")) {
            logger.warn(String.format(WebsocketConstantMessage.Server.WRONG_CLIENT_ORIGIN_IN_HEADER, webSocket.getRemoteSocketAddress().getHostString()));
            webSocket.close(1008, "X-Client-Origin Header cannot be minecraft");
            return;
        }

        String serverName = unicodeDecode(originServerName);
        if (serverName.isEmpty()) {
            logger.warn(String.format(WebsocketConstantMessage.Server.PARSE_SERVER_NAME_FAILED_IN_HEADER, webSocket.getRemoteSocketAddress().getHostString()));
            webSocket.close(1008, "X-Self-name Header cannot be empty");
            return;
        }
        logger.info(String.format(WebsocketConstantMessage.Server.CLIENT_CONNECTED_SUCCESSFULLY, webSocket.getRemoteSocketAddress().getHostString()));

        String accessToken = clientHandshake.getFieldValue("Authorization");
        if (!config.getAccessToken().isEmpty() && !accessToken.equals("Bearer " + config.getAccessToken())) {
            logger.warn(String.format(WebsocketConstantMessage.Server.WRONG_ACCESS_TOKEN_IN_HEADER, getClientAddress(webSocket)));
            webSocket.close(1008, "Authorization Header is wrong");
            return;
        }

    }

    @Override
    public void onClose(WebSocket webSocket, int code, String reason, boolean remote) {
        String closeReason = remote ? WebsocketConstantMessage.Server.CLIENT_DISCONNECTED : WebsocketConstantMessage.Server.CLIENT_HAD_BEEN_DISCONNECTED;
        logger.info(String.format(closeReason, webSocket.getRemoteSocketAddress().getHostString()));
    }

    @Override
    public void onMessage(WebSocket webSocket, String message) {
        if (config.isEnableMcQQ()) {
            try {
                handleProtocolMessage.handleWebSocketJson(message);
            } catch (Exception e) {
                logger.warn(String.format(WebsocketConstantMessage.PARSE_MESSAGE_ERROR_ON_MESSAGE, webSocket.getRemoteSocketAddress()));
                logger.warn(e.getMessage());
            }
        }
    }

    @Override
    public void onError(WebSocket webSocket, Exception exception) {
        logger.warn(String.format(WebsocketConstantMessage.Server.ON_ERROR, webSocket.getRemoteSocketAddress().toString().replaceFirst("/", ""), exception.getMessage()));
    }

    @Override
    public void onStart() {
        logger.info(WebsocketConstantMessage.Server.ON_START);
    }
}
