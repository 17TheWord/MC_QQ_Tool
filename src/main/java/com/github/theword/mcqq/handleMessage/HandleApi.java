package com.github.theword.mcqq.handleMessage;

import com.github.theword.mcqq.returnBody.ActionbarReturnBody;
import com.github.theword.mcqq.returnBody.MessageReturnBody;
import com.github.theword.mcqq.returnBody.SendTitleReturnBody;

public interface HandleApi {

    /**
     * 广播消息
     *
     * @param messageReturnBody 消息体
     */
    void handleBroadcastMessage(MessageReturnBody messageReturnBody);

    /**
     * 广播 Send Title 消息
     *
     * @param sendTitleReturnBody Send Title 消息体
     */
    void handleSendTitleMessage(SendTitleReturnBody sendTitleReturnBody);

    /**
     * 广播 Action Bar 消息
     *
     * @param actionbarReturnBody Action Bar 消息体
     */
    void handleActionBarMessage(ActionbarReturnBody actionbarReturnBody);
}
