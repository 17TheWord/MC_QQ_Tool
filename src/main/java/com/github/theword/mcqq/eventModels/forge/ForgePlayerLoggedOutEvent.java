package com.github.theword.mcqq.eventModels.forge;

import com.github.theword.mcqq.eventModels.base.BasePlayerQuitEvent;

public final class ForgePlayerLoggedOutEvent extends BasePlayerQuitEvent {
    public ForgePlayerLoggedOutEvent(ForgeServerPlayer player) {
        super("PlayerLoggedOutEvent", player);
    }
}
