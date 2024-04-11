package com.github.theword.mcqq.eventModels.fabric;

import com.github.theword.mcqq.eventModels.base.BasePlayerDeathEvent;

public class FabricServerLivingEntityAfterDeathEvent extends BasePlayerDeathEvent {

    public FabricServerLivingEntityAfterDeathEvent(String messageId, FabricServerPlayer player, String message) {
        super("ServerLivingEntityAfterDeathEvent", messageId, player, message);
    }
}
