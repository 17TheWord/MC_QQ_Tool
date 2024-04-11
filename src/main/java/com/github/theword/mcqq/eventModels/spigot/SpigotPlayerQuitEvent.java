package com.github.theword.mcqq.eventModels.spigot;

import com.github.theword.mcqq.eventModels.base.BasePlayerQuitEvent;

public class SpigotPlayerQuitEvent extends BasePlayerQuitEvent {

    public SpigotPlayerQuitEvent( SpigotPlayer player) {
        super("PlayerQuitEvent", player);
    }
}
