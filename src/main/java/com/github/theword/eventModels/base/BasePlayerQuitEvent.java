package com.github.theword.eventModels.base;

public class BasePlayerQuitEvent extends BaseNoticeEvent {
    public BasePlayerQuitEvent(String eventName, BasePlayer player) {
        super( eventName, "quit", player);
    }
}
