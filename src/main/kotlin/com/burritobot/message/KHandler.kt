package com.burritobot.message

import com.burritobot.model.Emoji
import com.burritobot.utils.sign
import dev.kord.core.behavior.channel.createMessage
import dev.kord.core.event.message.MessageCreateEvent

class KHandler : MessageHandler {
    override suspend fun handle(event: MessageCreateEvent, normalizedContent: String) {
        if (normalizedContent == "k") {
            val message = event.message
            val referencedMessageId = message.referencedMessage?.id
            message.delete()
            message.channel.createMessage {
                content = Emoji.CHECK.formatted
                messageReference = referencedMessageId
            }.sign(message.author?.id)
        }
    }
}
