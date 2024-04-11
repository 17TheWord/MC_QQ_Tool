package com.github.theword.mcqq.eventModels.spigot;

import com.github.theword.mcqq.eventModels.base.BaseCommandEvent;

public class SpigotPlayerCommandPreprocessEvent extends BaseCommandEvent {

    public SpigotPlayerCommandPreprocessEvent( SpigotPlayer player, String command) {
        super("PlayerCommandPreprocessEvent", "", player, command);
    }
}
