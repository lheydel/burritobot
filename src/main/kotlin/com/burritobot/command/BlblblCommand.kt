package com.burritobot.command

import dev.kord.common.entity.Snowflake
import dev.kord.core.Kord
import dev.kord.core.behavior.interaction.respondPublic
import dev.kord.core.event.interaction.GuildChatInputCommandInteractionCreateEvent

class BlblblCommand : SlashCommand {
    override val name = "blblbl"
    override val description = "Say burrito catchphrase"

    override suspend fun register(kord: Kord, guildId: Snowflake) {
        kord.createGuildChatInputCommand(guildId, name, description)
    }

    override suspend fun handle(event: GuildChatInputCommandInteractionCreateEvent) {
        event.interaction.respondPublic { content = "I'm a burritooooo!" }
    }
}
