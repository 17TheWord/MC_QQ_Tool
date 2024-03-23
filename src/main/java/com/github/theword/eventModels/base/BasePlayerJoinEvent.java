package com.github.theword.eventModels.base;

public class BasePlayerJoinEvent extends BaseNoticeEvent {
    public BasePlayerJoinEvent(String eventName, BasePlayer player) {
        super( eventName, "join", player);
    }
}
