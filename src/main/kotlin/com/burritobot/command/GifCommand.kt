package com.burritobot.command

import com.burritobot.service.TenorClient
import dev.kord.common.entity.Snowflake
import dev.kord.core.Kord
import dev.kord.core.behavior.interaction.response.respond
import dev.kord.core.event.interaction.GuildChatInputCommandInteractionCreateEvent
import dev.kord.rest.builder.interaction.string

class GifCommand(
    private val tenorClient: TenorClient
) : SlashCommand {
    override val name = "gif"
    override val description = "Search and send a GIF"

    override suspend fun register(kord: Kord, guildId: Snowflake) {
        kord.createGuildChatInputCommand(guildId, name, description) {
            string("query", "Search term (defaults to 'burrito')") {
                required = false
            }
        }
    }

    override suspend fun handle(event: GuildChatInputCommandInteractionCreateEvent) {
        val query = event.interaction.command.strings["query"] ?: "burrito"

        val deferred = event.interaction.deferPublicResponse()
        val gifUrl = tenorClient.searchGif(query)

        if (gifUrl != null) {
            deferred.respond { content = gifUrl }
        } else {
            deferred.respond { content = "Aucun gif trouvé pour la requête : \"$query\"" }
        }
    }
}
