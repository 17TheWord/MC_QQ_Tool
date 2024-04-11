package com.github.theword.mcqq.eventModels.spigot;

import com.github.theword.mcqq.eventModels.base.BasePlayerDeathEvent;

public class SpigotPlayerDeathEvent extends BasePlayerDeathEvent {

    public SpigotPlayerDeathEvent( SpigotPlayer player, String message) {
        super( "PlayerDeathEvent", "", player, message);
    }
}
