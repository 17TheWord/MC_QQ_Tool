package com.github.theword.eventModels.base;

public class BasePlayerQuitEvent extends BaseNoticeEvent {
    public BasePlayerQuitEvent(String serverName, String eventName, BasePlayer player) {
        super(serverName, eventName, "quit", player);
    }
}
