package com.burritobot.message

import com.burritobot.model.Emoji
import com.burritobot.utils.sign
import dev.kord.core.event.message.MessageCreateEvent

class KHandler : MessageHandler {
    override suspend fun handle(event: MessageCreateEvent, normalizedContent: String) {
        if (normalizedContent == "k") {
            val message = event.message
            message.delete()
            message.channel
                .createMessage(Emoji.CHECK.formatted)
                .sign(message.author?.id)
        }
    }
}
