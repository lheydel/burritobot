package com.burritobot

import com.burritobot.command.SlashCommandHandler
import com.burritobot.model.Emoji
import com.burritobot.utils.chance
import com.burritobot.utils.normalize
import com.burritobot.utils.sign
import dev.kord.core.Kord
import dev.kord.core.event.interaction.GuildChatInputCommandInteractionCreateEvent
import dev.kord.core.event.message.MessageCreateEvent
import dev.kord.core.on
import org.slf4j.LoggerFactory
import kotlin.random.Random

class BurritoBot(
    private val kord: Kord,
    private val slashCommandHandler: SlashCommandHandler,
    private val reactionHandler: ReactionHandler
) {
    private val logger = LoggerFactory.getLogger(BurritoBot::class.java)

    fun setupListeners() {
        kord.on<GuildChatInputCommandInteractionCreateEvent> {
            slashCommandHandler.handle(this)
        }

        kord.on<MessageCreateEvent> {
            val content = message.content.normalize()

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
