package com.burritobot.message

import com.burritobot.model.Emoji
import dev.kord.core.event.message.MessageCreateEvent

class BotPueHandler : MessageHandler {
    override suspend fun handle(event: MessageCreateEvent, normalizedContent: String) {
        if (listOf("bot", "pue").all { normalizedContent.contains(it) }) {
            event.message.addReaction(Emoji.SAD.toReaction())
        }
    }
}
