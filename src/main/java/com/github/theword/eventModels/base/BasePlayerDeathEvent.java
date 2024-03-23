package com.github.theword.eventModels.base;

public class BasePlayerDeathEvent extends BaseMessageEvent {

    public BasePlayerDeathEvent(String serverName, String eventName, String messageId, BasePlayer player, String message) {
        super(serverName, eventName, "death", messageId, player, message);
    }
}
