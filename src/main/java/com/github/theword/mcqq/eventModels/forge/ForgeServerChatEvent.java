package com.github.theword.mcqq.eventModels.forge;

import com.github.theword.mcqq.eventModels.base.BasePlayerChatEvent;

public final class ForgeServerChatEvent extends BasePlayerChatEvent {
    public ForgeServerChatEvent(String messageId, ForgeServerPlayer player, String message) {
        super("ServerChatEvent", messageId, player, message);
    }

}
