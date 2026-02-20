package com.burritobot.command

import com.burritobot.model.Emoji
import dev.kord.common.entity.Snowflake
import dev.kord.core.Kord
import dev.kord.core.behavior.interaction.respondPublic
import dev.kord.core.event.interaction.GuildChatInputCommandInteractionCreateEvent

class OkCommand : SlashCommand {
    override val name = "ok"
    override val description = "Send OK emoji"

    override suspend fun register(kord: Kord, guildId: Snowflake) {
        kord.createGuildChatInputCommand(guildId, name, description)
    }

    override suspend fun handle(event: GuildChatInputCommandInteractionCreateEvent) {
        event.interaction.respondPublic { content = Emoji.OK1.formatted }
    }
}
