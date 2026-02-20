package com.burritobot.message

import com.burritobot.model.Emoji
import com.burritobot.utils.chance
import dev.kord.core.event.message.MessageCreateEvent
import kotlin.random.Random

class QuestionHandler : MessageHandler {
    override suspend fun handle(event: MessageCreateEvent, normalizedContent: String) {
        if (normalizedContent.endsWith("?") && chance(0.01)) {
            val rdm = Random.nextDouble()
            val response = when {
                rdm <= 0.495 -> Emoji.OUI.formatted
                rdm <= 0.99 -> Emoji.NON.formatted
                else -> Emoji.MAYBE.formatted
            }
            event.message.channel.createMessage(response)
        }
    }
}
