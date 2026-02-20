package com.burritobot

import com.burritobot.model.Emoji
import com.burritobot.service.TenorClient
import com.burritobot.utils.chance
import dev.kord.core.entity.Message
import dev.kord.core.entity.ReactionEmoji

class ReactionHandler(
    private val tenorClient: TenorClient
) {
    private val reactions = listOf(
        Reaction(
            patterns = listOf("burrito", "burrital"),
            action = { msg -> msg.addReaction(Emoji.BURRITAL.toReaction()) }
        ),
        Reaction(
            patterns = listOf(Emoji.NINENINENINE.formatted, "666"),
            action = { msg -> msg.addReaction(Emoji.SIXSIXSIX.toReaction()) }
        ),
        Reaction(
            patterns = listOf(Emoji.SIXSIXSIX.formatted),
            action = { msg -> msg.addReaction(Emoji.NINENINENINE.toReaction()) }
        ),
        Reaction(
            patterns = listOf("metal"),
            action = { msg -> msg.addReaction(ReactionEmoji.Unicode("\uD83E\uDD18")) }
        ),
        Reaction(
            patterns = listOf("noel"),
            action = { msg -> msg.addReaction(Emoji.BURRITOEL.toReaction()) }
        ),
        Reaction(
            patterns = listOf("itk"),
            action = { msg ->
                if (chance(0.1)) {
                    tenorClient.searchGif("cow")?.let { gifUrl ->
                        msg.channel.createMessage(gifUrl)
                    }
                }
            }
        )
    )

    suspend fun handle(message: Message, normalizedContent: String) {
        reactions
            .filter { it.matches(normalizedContent) }
            .forEach { it.action(message) }
    }
}

private data class Reaction(
    val patterns: List<String>,
    val action: suspend (Message) -> Unit
) {
    fun matches(content: String): Boolean = patterns.any { content.contains(it) }
}
