package com.burritobot

import com.burritobot.command.SlashCommandHandler
import com.burritobot.command.SlashCommandRegistry
import dev.kord.core.Kord
import dev.kord.core.event.gateway.ReadyEvent
import dev.kord.core.on
import dev.kord.gateway.Intent
import dev.kord.gateway.PrivilegedIntent
import kotlinx.coroutines.coroutineScope
import org.koin.core.context.startKoin
import org.koin.core.parameter.parametersOf
import org.koin.java.KoinJavaComponent.getKoin
import org.slf4j.LoggerFactory

private val logger = LoggerFactory.getLogger("BurritoBot")

@OptIn(PrivilegedIntent::class)
suspend fun main() = coroutineScope {
    startKoin {
        modules(appModule)
    }

    val kord = Kord(Config.botToken)
    val commandHandler: SlashCommandHandler = getKoin().get()

    val registry = SlashCommandRegistry(kord, commandHandler)
    registry.registerCommands()

    val bot: BurritoBot = getKoin().get { parametersOf(kord) }
    bot.setupListeners()

    kord.on<ReadyEvent> {
        logger.info("Logged in as ${kord.getSelf().username}")
    }

    kord.login {
        intents += Intent.GuildMessages
        intents += Intent.MessageContent
        intents += Intent.GuildMessageReactions
    }
}
