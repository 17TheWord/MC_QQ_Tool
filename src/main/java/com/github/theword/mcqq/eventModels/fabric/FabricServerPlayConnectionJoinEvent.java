package com.github.theword.mcqq.eventModels.fabric;

import com.github.theword.mcqq.eventModels.base.BasePlayerJoinEvent;

public class FabricServerPlayConnectionJoinEvent extends BasePlayerJoinEvent {
    public FabricServerPlayConnectionJoinEvent(FabricServerPlayer player) {
        super("ServerPlayConnectionJoinEvent", player);
    }
}
