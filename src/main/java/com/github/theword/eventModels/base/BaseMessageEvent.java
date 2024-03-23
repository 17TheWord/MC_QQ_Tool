package com.github.theword.eventModels.base;

import com.google.gson.annotations.SerializedName;

public class BaseMessageEvent extends BaseEvent {
    @SerializedName("message_id")
    private String messageId;
    private final BasePlayer player;
    private final String message;

    public BaseMessageEvent(String serverName, String eventName, String subType, String messageId, BasePlayer player, String message) {
        super(serverName, eventName, "message", subType);
        this.messageId = messageId;
        this.player = player;
        this.message = message;
    }
}
