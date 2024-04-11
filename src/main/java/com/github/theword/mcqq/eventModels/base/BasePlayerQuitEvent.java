package com.github.theword.mcqq.eventModels.base;

public class BasePlayerQuitEvent extends BaseNoticeEvent {
    public BasePlayerQuitEvent(String eventName, BasePlayer player) {
        super( eventName, "quit", player);
    }
}
