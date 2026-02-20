package com.burritobot.message

import com.burritobot.model.Emoji
import com.burritobot.utils.chance
import dev.kord.core.event.message.MessageCreateEvent
import kotlin.random.Random

class RandomOkHandler : MessageHandler {
    override suspend fun handle(event: MessageCreateEvent, normalizedContent: String) {
        if (chance(0.002)) {
            val ok = if (Random.nextBoolean()) Emoji.OK1 else Emoji.OK2
            event.message.channel.createMessage(ok.formatted)
        }
    }
}
