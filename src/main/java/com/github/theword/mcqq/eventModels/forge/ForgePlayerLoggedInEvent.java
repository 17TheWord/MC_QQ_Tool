package com.github.theword.mcqq.eventModels.forge;

import com.github.theword.mcqq.eventModels.base.BasePlayerJoinEvent;

public final class ForgePlayerLoggedInEvent extends BasePlayerJoinEvent {
    public ForgePlayerLoggedInEvent(ForgeServerPlayer player) {
        super("PlayerLoggedInEvent", player);
    }
}
