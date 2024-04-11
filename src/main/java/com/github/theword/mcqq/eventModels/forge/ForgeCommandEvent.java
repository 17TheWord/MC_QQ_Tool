package com.github.theword.mcqq.eventModels.forge;

import com.github.theword.mcqq.eventModels.base.BaseCommandEvent;

public class ForgeCommandEvent extends BaseCommandEvent {
    public ForgeCommandEvent(String messageId, ForgeServerPlayer player, String command) {
        super("CommandEvent", messageId, player, command);
    }
}
