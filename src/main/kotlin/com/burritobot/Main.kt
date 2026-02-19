package com.burritobot

import com.burritobot.config.Config
import com.burritobot.di.appModule
import dev.kord.core.Kord
import dev.kord.core.event.gateway.ReadyEvent
import dev.kord.core.on
import dev.kord.gateway.Intent
import dev.kord.gateway.PrivilegedIntent
import org.koin.core.context.startKoin
import org.slf4j.LoggerFactory

private val logger = LoggerFactory.getLogger("BurritoBot")

@OptIn(PrivilegedIntent::class)
suspend fun main() {
    startKoin {
        modules(appModule)
    }

    val kord = Kord(Config.botToken)

    kord.on<ReadyEvent> {
        logger.info("Logged in as ${kord.getSelf().username}")
    }

    kord.login {
        intents += Intent.GuildMessages
        intents += Intent.MessageContent
        intents += Intent.GuildMessageReactions
    }
}
