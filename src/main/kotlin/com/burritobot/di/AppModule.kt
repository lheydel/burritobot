package com.burritobot.di

import com.burritobot.BurritoBot
import com.burritobot.command.BlblblCommand
import com.burritobot.command.CmdCommand
import com.burritobot.command.Command
import com.burritobot.command.CommandHandler
import com.burritobot.command.GifCommand
import com.burritobot.command.InsultCommand
import com.burritobot.command.NoiseCommand
import com.burritobot.command.OkCommand
import com.burritobot.command.PdCommand
import com.burritobot.config.Config
import com.burritobot.external.TenorClient
import com.burritobot.insult.InsultService
import com.burritobot.reaction.ReactionHandler
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

    single<List<Command>> {
        listOf(
            CmdCommand { get() },
            BlblblCommand(),
            OkCommand(),
            PdCommand(),
            NoiseCommand(),
            GifCommand(get()),
            InsultCommand(get())
        )
    }

    single { CommandHandler(get()) }

    factory { (kord: Kord) ->
        BurritoBot(kord, get(), get())
    }
}
