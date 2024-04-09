package com.github.theword.eventModels.fabric;

import com.github.theword.eventModels.base.BaseCommandEvent;

public class FabricServerCommandMessageEvent extends BaseCommandEvent {
    public FabricServerCommandMessageEvent(String messageId, FabricServerPlayer player, String message) {
        super("ServerCommandMessageEvent", messageId, player, message);
    }
}
