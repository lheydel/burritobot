package com.burritobot

import com.burritobot.command.BlblblCommand
import com.burritobot.command.GifCommand
import com.burritobot.command.HelpCommand
import com.burritobot.command.InsultCommand
import com.burritobot.command.OkCommand
import com.burritobot.command.PdCommand
import com.burritobot.command.SlashCommand
import com.burritobot.command.SlashCommandHandler
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
    single { ReactionHandler(get()) }

    single<List<SlashCommand>> {
        val commands = mutableListOf<SlashCommand>(
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

    factory { (kord: Kord) ->
        BurritoBot(kord, get(), get())
    }
}
