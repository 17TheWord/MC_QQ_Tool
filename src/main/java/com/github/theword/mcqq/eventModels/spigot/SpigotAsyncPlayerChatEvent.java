package com.github.theword.mcqq.eventModels.spigot;

import com.github.theword.mcqq.eventModels.base.BasePlayerChatEvent;

public class SpigotAsyncPlayerChatEvent extends BasePlayerChatEvent {

    public SpigotAsyncPlayerChatEvent(SpigotPlayer player, String message) {
        super("AsyncPlayerChatEvent", "", player, message);
    }
}
