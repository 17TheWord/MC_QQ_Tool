package com.github.theword.mcqq.websocket;

import com.github.theword.mcqq.constant.WebsocketConstantMessage;
import com.github.theword.mcqq.handleMessage.HandleProtocolMessage;
import lombok.Getter;
import lombok.SneakyThrows;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.ConnectException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Timer;
import java.util.TimerTask;

import static com.github.theword.mcqq.utils.Tool.config;
import static com.github.theword.mcqq.utils.Tool.logger;


public class WsClient extends WebSocketClient {
    private int reconnectTimes = 1;
    @Getter
    private final Timer timer = new Timer();
    private final HandleProtocolMessage handleProtocolMessage = new HandleProtocolMessage();

    @SneakyThrows
    public WsClient(URI uri) {
        super(uri);
        addHeader("x-self-name", URLEncoder.encode(config.getServerName(), StandardCharsets.UTF_8.toString()));
        addHeader("x-client-origin", "minecraft");
        if (!config.getAccessToken().isEmpty())
            addHeader("Authorization", "Bearer " + config.getAccessToken());
    }

    /**
     * 连接打开时
     *
     * @param serverHandshake ServerHandshake
     */
    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        logger.info(String.format(WebsocketConstantMessage.Client.ON_OPEN, getURI()));
        reconnectTimes = 1;
    }

    /**
     * 收到消息时触发
     * 向服务器游戏内公屏发送信息
     */
    @Override
    public void onMessage(String message) {
        if (config.isEnableMcQQ()) {
            try {
                handleProtocolMessage.handleWebSocketJson(this, message);
            } catch (Exception e) {
                logger.warn(String.format(WebsocketConstantMessage.PARSE_MESSAGE_ERROR_ON_MESSAGE, getURI()));
                logger.warn(e.getMessage());
            }
        }
    }

    /**
     * 关闭时
     *
     * @param code   关闭码
     * @param reason 关闭信息
     * @param remote 是否关闭
     */
    @Override
    public void onClose(int code, String reason, boolean remote) {
        if (remote && reconnectTimes <= config.getReconnectMaxTimes()) {
            reconnectWebsocket();
        }
    }

    public void reconnectWebsocket() {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                reconnect();
            }
        };
        timer.schedule(timerTask, config.getReconnectInterval() * 1000L);
    }

    public void stopWithoutReconnect(int code, String reason) {
        timer.cancel();
        close(code, reason);
    }

    @Override
    public void reconnect() {
        if (config.isEnableReconnectMessage()) {
            logger.info(String.format(WebsocketConstantMessage.Client.RECONNECT_MESSAGE, getURI(), reconnectTimes));
        }
        reconnectTimes++;
        super.reconnect();
        if (reconnectTimes == config.getReconnectMaxTimes() + 1) {
            logger.info(String.format(WebsocketConstantMessage.Client.RECONNECT_TIMES_REACH, getURI()));
        }
    }

    /**
     * 触发异常时
     *
     * @param exception 所有异常
     */
    @Override
    public void onError(Exception exception) {
        logger.warn(String.format(WebsocketConstantMessage.Client.ON_ERROR, getURI(), exception.getMessage()));
        if (exception instanceof ConnectException && exception.getMessage().equals("Connection refused: connect") && reconnectTimes <= config.getReconnectMaxTimes()) {
            reconnectWebsocket();
        }
    }

    @Override
    public void send(String text) {
        if (isOpen()) {
            super.send(text);
        } else {
            logger.info(String.format(WebsocketConstantMessage.Client.IS_NOT_OPEN_WHEN_SEND_MESSAGE, getURI()));
        }
    }
}
