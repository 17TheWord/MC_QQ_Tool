package com.github.theword.eventModels.base;

public class BasePlayerChatEvent extends BaseMessageEvent {
    public BasePlayerChatEvent(String serverName, String eventName, String messageId, BasePlayer player, String message) {
        super(serverName, eventName, "chat", messageId, player, message);
    }

}
