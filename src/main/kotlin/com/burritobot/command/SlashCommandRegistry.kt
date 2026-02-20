package com.burritobot.command

import com.burritobot.Config
import com.burritobot.utils.mapAsync
import dev.kord.core.Kord
import org.slf4j.LoggerFactory

class SlashCommandRegistry(
    private val kord: Kord,
    private val commandHandler: SlashCommandHandler
) {
    private val logger = LoggerFactory.getLogger(SlashCommandRegistry::class.java)

    suspend fun registerCommands() {
        val commands = commandHandler.getCommands()

        Config.guildIds.mapAsync { guildId ->
            logger.info("Registering slash commands for guild $guildId")

            commands.mapAsync { command ->
                command.register(kord, guildId)
            }

            logger.info("Slash commands registered for guild $guildId")
        }
    }
}
