package com.github.theword.eventModels.forge;

import com.github.theword.eventModels.base.BasePlayerQuitEvent;

public final class ForgePlayerLoggedOutEvent extends BasePlayerQuitEvent {
    public ForgePlayerLoggedOutEvent(ForgeServerPlayer player) {
        super("PlayerLoggedOutEvent", player);
    }
}
