package com.burritobot.message

import dev.kord.core.event.message.MessageCreateEvent

interface MessageHandler {
    suspend fun handle(event: MessageCreateEvent, normalizedContent: String)
}
