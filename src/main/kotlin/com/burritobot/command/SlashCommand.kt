package com.burritobot.command

import dev.kord.common.entity.Snowflake
import dev.kord.core.Kord
import dev.kord.core.event.interaction.GuildChatInputCommandInteractionCreateEvent

interface SlashCommand {
    val name: String
    val description: String

    suspend fun register(kord: Kord, guildId: Snowflake)
    suspend fun handle(event: GuildChatInputCommandInteractionCreateEvent)
}
