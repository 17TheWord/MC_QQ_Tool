package com.github.theword.eventModels.forge;

import com.github.theword.eventModels.base.BasePlayerJoinEvent;

public final class ForgePlayerLoggedInEvent extends BasePlayerJoinEvent {
    public ForgePlayerLoggedInEvent(ForgeServerPlayer player) {
        super("ForgePlayerLoggedInEvent", player);
    }
}
