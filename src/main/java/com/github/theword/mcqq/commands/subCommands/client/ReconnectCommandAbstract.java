package com.github.theword.mcqq.commands.subCommands.client;

import com.github.theword.mcqq.commands.SubCommand;

public abstract class ReconnectCommandAbstract implements SubCommand {

    /**
     * @return reconnect
     */
    @Override
    public String getName() {
        return "reconnect";
    }

    /**
     * @return 重新连接 Websocket Clients。
     */
    @Override
    public String getDescription() {
        return "重新连接 Websocket Clients。";
    }

    /**
     * @return 使用：/mcqq client reconnect [all]
     */
    @Override
    public String getUsage() {
        return "使用：/mcqq client reconnect [all]";
    }
}
