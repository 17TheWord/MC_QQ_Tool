package com.github.theword.mcqq.commands.subCommands;

import com.github.theword.mcqq.commands.SubCommand;

public abstract class HelpCommandAbstract implements SubCommand {

    /**
     * 获取命令名称
     *
     * @return help
     */
    @Override
    public String getName() {
        return "help";
    }

    /**
     * 获取命令描述
     *
     * @return 获取命令帮助
     */
    @Override
    public String getDescription() {
        return "获取命令帮助";
    }

    /**
     * 获取命令用法
     *
     * @return 使用：/mcqq
     */
    @Override
    public String getUsage() {
        return "使用：/mcqq";
    }
}
