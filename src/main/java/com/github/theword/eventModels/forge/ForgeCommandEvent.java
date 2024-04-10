package com.github.theword.eventModels.forge;

import com.github.theword.eventModels.base.BaseCommandEvent;

public class ForgeCommandEvent extends BaseCommandEvent {
    public ForgeCommandEvent(String messageId, ForgeServerPlayer player, String command) {
        super("CommandEvent", messageId, player, command);
    }
}
