package com.burritobot

import com.burritobot.command.SlashCommandHandler
import com.burritobot.message.MessageHandlerRegistry
import com.burritobot.utils.normalize
import dev.kord.core.Kord
import dev.kord.core.event.interaction.GuildChatInputCommandInteractionCreateEvent
import dev.kord.core.event.message.MessageCreateEvent
import dev.kord.core.on
import org.slf4j.LoggerFactory

class BurritoBot(
    private val kord: Kord,
    private val slashCommandHandler: SlashCommandHandler,
    private val messageHandlerRegistry: MessageHandlerRegistry
) {
    private val logger = LoggerFactory.getLogger(BurritoBot::class.java)

    fun setupListeners() {
        kord.on<GuildChatInputCommandInteractionCreateEvent> {
            slashCommandHandler.handle(this)
        }

        kord.on<MessageCreateEvent> {
            val content = message.content.normalize()
            messageHandlerRegistry.handle(this, content)
        }
    }
}
