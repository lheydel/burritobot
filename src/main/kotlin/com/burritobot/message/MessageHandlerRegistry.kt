package com.burritobot.message

import dev.kord.core.event.message.MessageCreateEvent

class MessageHandlerRegistry(
    private val handlers: List<MessageHandler>
) {
    suspend fun handle(event: MessageCreateEvent, normalizedContent: String) {
        handlers.forEach { it.handle(event, normalizedContent) }
    }
}
