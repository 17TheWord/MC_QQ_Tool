package com.github.theword.eventModels.base;

public class BasePlayerJoinEvent extends BaseNoticeEvent {
    public BasePlayerJoinEvent(String serverName, String eventName, BasePlayer player) {
        super(serverName, eventName, "join", player);
    }
}
