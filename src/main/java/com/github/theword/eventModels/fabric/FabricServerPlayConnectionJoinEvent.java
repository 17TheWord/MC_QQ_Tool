package com.github.theword.eventModels.fabric;

import com.github.theword.eventModels.base.BasePlayerJoinEvent;

public class FabricServerPlayConnectionJoinEvent extends BasePlayerJoinEvent {
    public FabricServerPlayConnectionJoinEvent(FabricServerPlayer player) {
        super("ServerPlayConnectionJoinEvent", player);
    }
}