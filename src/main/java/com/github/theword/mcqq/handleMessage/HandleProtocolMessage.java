package com.github.theword.mcqq.handleMessage;

import com.github.theword.mcqq.constant.BaseConstant;
import com.github.theword.mcqq.returnBody.ActionbarReturnBody;
import com.github.theword.mcqq.returnBody.BaseReturnBody;
import com.github.theword.mcqq.returnBody.MessageReturnBody;
import com.github.theword.mcqq.returnBody.SendTitleReturnBody;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.java_websocket.WebSocket;

import static com.github.theword.mcqq.utils.Tool.handleApi;
import static com.github.theword.mcqq.utils.Tool.logger;

public class HandleProtocolMessage {

    /**
     * 处理websocket消息
     *
     * @param webSocket WebSocket
     * @param message   websocket消息
     */
    public void handleWebSocketJson(WebSocket webSocket, String message) {
        // 组合消息
        Gson gson = new Gson();
        BaseReturnBody baseReturnBody = gson.fromJson(message, BaseReturnBody.class);
        JsonElement data = baseReturnBody.getData();
        switch (baseReturnBody.getApi()) {
            case "broadcast":
            case "send_msg":
                MessageReturnBody messageList = gson.fromJson(data, MessageReturnBody.class);
                handleApi.handleBroadcastMessage(webSocket, messageList.getMessageList());
                break;
            case "send_title":
                SendTitleReturnBody sendTitleReturnBody = gson.fromJson(data, SendTitleReturnBody.class);
                handleApi.handleSendTitleMessage(webSocket, sendTitleReturnBody.getSendTitle());
                break;
            case "actionbar":
                ActionbarReturnBody actionMessageList = gson.fromJson(data, ActionbarReturnBody.class);
                handleApi.handleActionBarMessage(webSocket, actionMessageList.getMessageList());
                break;
            case "command":
                // TODO Support command
            default:
                logger.warn(BaseConstant.UNKNOWN_API + baseReturnBody.getApi());
                break;
        }
    }
}
