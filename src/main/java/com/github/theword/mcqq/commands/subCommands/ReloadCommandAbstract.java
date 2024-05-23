package com.github.theword.mcqq.commands.subCommands;

import com.github.theword.mcqq.commands.SubCommand;

public abstract class ReloadCommandAbstract implements SubCommand {

    /**
     * 获取命令名称
     *
     * @return reload
     */
    @Override
    public String getName() {
        return "reload";
    }

    /**
     * 获取命令描述
     *
     * @return 重载配置文件并重新连接所有 Websocket Client
     */
    @Override
    public String getDescription() {
        return "重载配置文件并重新连接所有 Websocket Client";
    }

    /**
     * 获取命令用法
     *
     * @return 使用：/mcqq reload
     */
    @Override
    public String getUsage() {
        return "使用：/mcqq reload";
    }
}
