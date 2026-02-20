package com.burritobot.command

import dev.kord.core.event.interaction.GuildChatInputCommandInteractionCreateEvent
import org.slf4j.LoggerFactory

class SlashCommandHandler(
    commands: List<SlashCommand>
) {
    private val logger = LoggerFactory.getLogger(SlashCommandHandler::class.java)
    private val commandMap = commands.associateBy { it.name }

    suspend fun handle(event: GuildChatInputCommandInteractionCreateEvent) {
        val commandName = event.interaction.command.rootName

        logger.debug("Handling slash command: $commandName")

        val command = commandMap[commandName]
        if (command != null) {
            command.handle(event)
        } else {
            logger.warn("Unknown command: $commandName")
        }
    }

    fun getCommands(): List<SlashCommand> = commandMap.values.toList()
}
