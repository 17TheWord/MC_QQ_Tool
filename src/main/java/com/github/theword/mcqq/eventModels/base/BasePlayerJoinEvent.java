package com.github.theword.mcqq.eventModels.base;

public class BasePlayerJoinEvent extends BaseNoticeEvent {
    public BasePlayerJoinEvent(String eventName, BasePlayer player) {
        super( eventName, "join", player);
    }
}
