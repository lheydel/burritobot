package com.burritobot.command

import com.burritobot.service.TenorClient
import com.burritobot.util.sign
import dev.kord.core.entity.Message

class GifCommand(
    private val tenorClient: TenorClient
) : Command {
    override val name = "gif"
    override val description = "Search and send a GIF"

    override suspend fun execute(message: Message, args: List<String>) {
        message.delete()
        val query = args.joinToString(" ").ifBlank { "burrito" }
        val gifUrl = tenorClient.searchGif(query)

        if (gifUrl != null) {
            val gifMessage = message.channel.createMessage(gifUrl)
            gifMessage.sign(message.author?.id)
        } else {
            message.channel.createMessage("Aucun gif trouvé pour la requête : \"$query\"")
        }
    }
}
