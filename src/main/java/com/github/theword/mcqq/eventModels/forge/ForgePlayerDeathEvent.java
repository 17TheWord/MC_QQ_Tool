package com.github.theword.mcqq.eventModels.forge;

import com.github.theword.mcqq.eventModels.base.BasePlayerDeathEvent;

public class ForgePlayerDeathEvent extends BasePlayerDeathEvent {

    public ForgePlayerDeathEvent(String messageId, ForgeServerPlayer player, String message) {
        super("PlayerDeathEvent", messageId, player, message);
    }
}
