package com.burritobot

import com.burritobot.command.BlblblCommand
import com.burritobot.command.GifCommand
import com.burritobot.command.HelpCommand
import com.burritobot.command.InsultCommand
import com.burritobot.command.OkCommand
import com.burritobot.command.PdCommand
import com.burritobot.command.SlashCommand
import com.burritobot.command.SlashCommandHandler
import com.burritobot.message.BotPueHandler
import com.burritobot.message.KHandler
import com.burritobot.message.MessageHandler
import com.burritobot.message.MessageHandlerRegistry
import com.burritobot.message.QuestionHandler
import com.burritobot.message.RandomOkHandler
import com.burritobot.message.ReactionHandler
import com.burritobot.service.InsultService
import com.burritobot.service.TenorClient
import dev.kord.core.Kord
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val appModule = module {
    single {
        HttpClient(CIO) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                })
            }
        }
    }

    single { TenorClient(get(), Config.tenorToken) }
    single { InsultService() }

    single<List<SlashCommand>> {
        val commands = mutableListOf(
            BlblblCommand(),
            OkCommand(),
            PdCommand(),
            GifCommand(get()),
            InsultCommand(get())
        )
        commands.add(HelpCommand(commands))
        commands
    }

    single { SlashCommandHandler(get()) }

    single<List<MessageHandler>> {
        listOf(
            QuestionHandler(),
            RandomOkHandler(),
            BotPueHandler(),
            KHandler(),
            ReactionHandler(get())
        )
    }

    single { MessageHandlerRegistry(get()) }

    factory { (kord: Kord) ->
        BurritoBot(kord, get(), get())
    }
}
