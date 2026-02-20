package com.burritobot.command

import com.burritobot.model.UserRepository
import com.burritobot.service.InsultService
import dev.kord.common.entity.Snowflake
import dev.kord.core.Kord
import dev.kord.core.behavior.interaction.respondPublic
import dev.kord.core.event.interaction.GuildChatInputCommandInteractionCreateEvent
import dev.kord.rest.builder.interaction.string

class InsultCommand(
    private val insultService: InsultService
) : SlashCommand {
    override val name = "insult"
    override val description = "Insult someone (or compliment bots)"

    override suspend fun register(kord: Kord, guildId: Snowflake) {
        kord.createGuildChatInputCommand(guildId, name, description) {
            string("target", "Who to insult") {
                required = true
            }
        }
    }

    override suspend fun handle(event: GuildChatInputCommandInteractionCreateEvent) {
        val target = event.interaction.command.strings["target"] ?: return

        val text = if (UserRepository.isBot(target)) {
            insultService.getCompliment()
        } else {
            insultService.getInsult()
        }
        val textToSend = text.replaceFirstChar { it.lowercase() }

        event.interaction.respondPublic { content = "$target, $textToSend" }
    }
}
