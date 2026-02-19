package com.burritobot.command

import dev.kord.core.entity.Message

class CmdCommand(
    private val commandHandler: () -> CommandHandler
) : Command {
    override val name = "cmd"
    override val description = "List available commands"

    override suspend fun execute(message: Message, args: List<String>) {
        message.delete()
        val commands = commandHandler().getCommandNames().filter { it != name }
        message.channel.createMessage("Commands: ${commands.joinToString(", ")}")
    }
}
