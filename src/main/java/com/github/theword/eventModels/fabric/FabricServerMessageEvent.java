package com.github.theword.eventModels.fabric;

import com.github.theword.eventModels.base.BasePlayerChatEvent;

public class FabricServerMessageEvent extends BasePlayerChatEvent {
    public FabricServerMessageEvent(String messageId, FabricServerPlayer player, String message) {
        super("ServerMessageEvent", messageId, player, message);
    }
}