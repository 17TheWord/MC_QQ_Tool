package com.github.theword.mcqq.eventModels.fabric;

import com.github.theword.mcqq.eventModels.base.BasePlayerQuitEvent;

public class FabricServerPlayConnectionDisconnectEvent extends BasePlayerQuitEvent {
    public FabricServerPlayConnectionDisconnectEvent(FabricServerPlayer player) {
        super("ServerPlayConnectionDisconnectEvent", player);
    }
}
