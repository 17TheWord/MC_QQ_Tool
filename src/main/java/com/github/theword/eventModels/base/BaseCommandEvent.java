package com.github.theword.eventModels.base;

public class BaseCommandEvent extends BaseMessageEvent {
    public BaseCommandEvent(String serverName, String eventName, String messageId, BasePlayer player, String command) {
        super(serverName, eventName, "player_command", messageId, player, command);
    }
}
