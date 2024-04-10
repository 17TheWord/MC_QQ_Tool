package com.github.theword.eventModels.spigot;

import com.github.theword.eventModels.base.BaseCommandEvent;

public class SpigotPlayerCommandPreprocessEvent extends BaseCommandEvent {

    public SpigotPlayerCommandPreprocessEvent( SpigotPlayer player, String command) {
        super("PlayerCommandPreprocessEvent", "", player, command);
    }
}
