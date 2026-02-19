package com.burritobot.command

import dev.kord.core.entity.Message

class CommandHandler(
    commands: List<Command>
) {
    private val commandMap = commands.associateBy { it.name }

    suspend fun handle(message: Message): Boolean {
        val content = message.content
        if (!content.startsWith("!")) return false

        val parts = content.removePrefix("!").split(" ")
        val cmdName = parts.first().lowercase()
        val args = parts.drop(1)

        val command = commandMap[cmdName] ?: return false
        command.execute(message, args)
        return true
    }

    fun getCommandNames(): List<String> = commandMap.keys.toList()
}
