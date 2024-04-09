package com.github.theword.eventModels.spigot;

import com.github.theword.eventModels.base.BasePlayerDeathEvent;

public class SpigotPlayerDeathEvent extends BasePlayerDeathEvent {

    public SpigotPlayerDeathEvent( SpigotPlayer player, String message) {
        super( "PlayerDeathEvent", "", player, message);
    }
}
