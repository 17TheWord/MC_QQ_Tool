package com.github.theword.mcqq.handleMessage;

import com.github.theword.mcqq.constant.BaseConstant;
import com.github.theword.mcqq.returnBody.ActionbarReturnBody;
import com.github.theword.mcqq.returnBody.BaseReturnBody;
import com.github.theword.mcqq.returnBody.MessageReturnBody;
import com.github.theword.mcqq.returnBody.SendTitleReturnBody;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import static com.github.theword.mcqq.utils.Tool.handleApi;
import static com.github.theword.mcqq.utils.Tool.logger;

public class HandleProtocolMessage {

    /**
     * 处理websocket消息
     *
     * @param message websocket消息
     */
    public void handleWebSocketJson(String message) {
        // 组合消息
        Gson gson = new Gson();
        BaseReturnBody baseReturnBody = gson.fromJson(message, BaseReturnBody.class);
        JsonElement data = baseReturnBody.getData();
        switch (baseReturnBody.getApi()) {
            case "broadcast":
                MessageReturnBody messageList = gson.fromJson(data, MessageReturnBody.class);
                handleApi.handleBroadcastMessage(messageList);
                break;
            case "send_title":
                SendTitleReturnBody sendTitleReturnBody = gson.fromJson(data, SendTitleReturnBody.class);
                handleApi.handleSendTitleMessage(sendTitleReturnBody);
                break;
            case "actionbar":
                ActionbarReturnBody actionMessageList = gson.fromJson(data, ActionbarReturnBody.class);
                handleApi.handleActionBarMessage(actionMessageList);
                break;
            default:
                logger.warn(BaseConstant.UNKNOWN_API + baseReturnBody.getApi());
                break;
        }
    }
}
