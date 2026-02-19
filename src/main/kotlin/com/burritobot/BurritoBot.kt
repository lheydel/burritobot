package com.burritobot

import com.burritobot.command.CommandHandler
import com.burritobot.model.Emoji
import com.burritobot.util.chance
import com.burritobot.util.normalize
import com.burritobot.util.sign
import dev.kord.core.Kord
import dev.kord.core.event.message.MessageCreateEvent
import dev.kord.core.on
import org.slf4j.LoggerFactory
import kotlin.random.Random

class BurritoBot(
    private val kord: Kord,
    private val commandHandler: CommandHandler,
    private val reactionHandler: ReactionHandler
) {
    private val logger = LoggerFactory.getLogger(BurritoBot::class.java)

    fun setupListeners() {
        kord.on<MessageCreateEvent> {
            val content = message.content.normalize()

            if (commandHandler.handle(message)) {
                return@on
            }

            if (content.endsWith("?") && chance(0.01)) {
                val rdm = Random.nextDouble()
                val response = when {
                    rdm <= 0.495 -> Emoji.OUI.formatted
                    rdm <= 0.99 -> Emoji.NON.formatted
                    else -> Emoji.MAYBE.formatted
                }
                message.channel.createMessage(response)
            }

            if (chance(0.002)) {
                val ok = if (Random.nextBoolean()) Emoji.OK1 else Emoji.OK2
                message.channel.createMessage(ok.formatted)
            }

            reactionHandler.handle(message, content)

            if (listOf("bot", "pue").all { content.contains(it) }) {
                message.addReaction(Emoji.SAD.toReaction())
            }

            if (content == "k") {
                message.delete()
                message.channel
                    .createMessage(Emoji.CHECK.formatted)
                    .sign(message.author?.id)
            }
        }
    }
}
