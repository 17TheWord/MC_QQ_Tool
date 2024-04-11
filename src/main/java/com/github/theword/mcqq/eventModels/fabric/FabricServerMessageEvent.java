package com.github.theword.mcqq.eventModels.fabric;

import com.github.theword.mcqq.eventModels.base.BasePlayerChatEvent;

public class FabricServerMessageEvent extends BasePlayerChatEvent {
    public FabricServerMessageEvent(String messageId, FabricServerPlayer player, String message) {
        super("ServerMessageEvent", messageId, player, message);
    }
}
