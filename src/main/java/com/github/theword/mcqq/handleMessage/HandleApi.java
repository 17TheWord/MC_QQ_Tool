package com.github.theword.mcqq.handleMessage;

import com.github.theword.mcqq.returnBody.returnModle.MyBaseComponent;
import com.github.theword.mcqq.returnBody.returnModle.MyTextComponent;
import com.github.theword.mcqq.returnBody.returnModle.SendTitle;
import org.java_websocket.WebSocket;

import java.util.List;

public interface HandleApi {

    /**
     * 广播消息
     *
     * @param webSocket   WebSocket
     * @param messageList 消息列表
     */
    void handleBroadcastMessage(WebSocket webSocket, List<MyTextComponent> messageList);

    /**
     * 广播 Send Title 消息
     *
     * @param webSocket WebSocket
     * @param sendTitle Send Title
     */
    void handleSendTitleMessage(WebSocket webSocket, SendTitle sendTitle);

    /**
     * 广播 Action Bar 消息
     *
     * @param webSocket   WebSocket
     * @param messageList Action Bar 消息列表
     */
    void handleActionBarMessage(WebSocket webSocket, List<MyBaseComponent> messageList);
}
