package com.burritobot.command

import dev.kord.common.entity.Snowflake
import dev.kord.core.Kord
import dev.kord.core.behavior.interaction.response.respond
import dev.kord.core.event.interaction.GuildChatInputCommandInteractionCreateEvent

class HelpCommand(
    private val commands: List<SlashCommand>
) : SlashCommand {
    override val name = "help"
    override val description = "List available commands"

    override suspend fun register(kord: Kord, guildId: Snowflake) {
        kord.createGuildChatInputCommand(guildId, name, description)
    }

    override suspend fun handle(event: GuildChatInputCommandInteractionCreateEvent) {
        val deferred = event.interaction.deferEphemeralResponse()

        val commandList = commands
            .sortedBy { it.name }
            .joinToString("\n") { "/${it.name} - ${it.description}" }

        deferred.respond {
            content = "**Available commands:**\n$commandList"
        }
    }
}
