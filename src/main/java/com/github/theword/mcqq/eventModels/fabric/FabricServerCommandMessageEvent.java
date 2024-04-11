package com.github.theword.mcqq.eventModels.fabric;

import com.github.theword.mcqq.eventModels.base.BaseCommandEvent;

public class FabricServerCommandMessageEvent extends BaseCommandEvent {
    public FabricServerCommandMessageEvent(String messageId, FabricServerPlayer player, String message) {
        super("ServerCommandMessageEvent", messageId, player, message);
    }
}
