package com.github.theword.mcqq.eventModels.spigot;

import com.github.theword.mcqq.eventModels.base.BasePlayerJoinEvent;

public class SpigotPlayerJoinEvent extends BasePlayerJoinEvent {

    public SpigotPlayerJoinEvent( SpigotPlayer player) {
        super( "PlayerJoinEvent", player);
    }

}
