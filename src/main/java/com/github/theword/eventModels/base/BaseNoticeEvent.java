package com.github.theword.eventModels.base;


public class BaseNoticeEvent extends BaseEvent {
    private final BasePlayer player;

    public BaseNoticeEvent(String serverName, String eventName, String subType, BasePlayer player) {
        super(serverName, eventName, "notice", subType);
        this.player = player;
    }

}
