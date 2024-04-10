package com.github.theword.eventModels.fabric;

import com.github.theword.eventModels.base.BasePlayerQuitEvent;

public class FabricServerPlayConnectionDisconnectEvent extends BasePlayerQuitEvent {
    public FabricServerPlayConnectionDisconnectEvent(FabricServerPlayer player) {
        super("ServerPlayConnectionDisconnectEvent", player);
    }
}
