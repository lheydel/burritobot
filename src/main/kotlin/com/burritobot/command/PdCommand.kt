package com.burritobot.command

import com.burritobot.model.Emoji
import dev.kord.common.entity.Snowflake
import dev.kord.core.Kord
import dev.kord.core.behavior.interaction.respondPublic
import dev.kord.core.event.interaction.GuildChatInputCommandInteractionCreateEvent

class PdCommand : SlashCommand {
    override val name = "pd"
    override val description = "Send PD emoji"

    override suspend fun register(kord: Kord, guildId: Snowflake) {
        kord.createGuildChatInputCommand(guildId, name, description)
    }

    override suspend fun handle(event: GuildChatInputCommandInteractionCreateEvent) {
        event.interaction.respondPublic { content = Emoji.PD.formatted }
    }
}
