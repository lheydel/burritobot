package com.burritobot.command

import dev.kord.core.behavior.channel.createMessage
import dev.kord.core.entity.Message

class NoiseCommand : Command {
    override val name = "noise"
    override val description = "Text-to-speech message"

    override suspend fun execute(message: Message, args: List<String>) {
        message.delete()
        val verbose = args.contains("-v")
        val text = args.filter { it != "-v" }.joinToString(" ")

        val ttsMessage = message.channel.createMessage {
            content = text
            tts = true
        }

        if (!verbose) {
            ttsMessage.delete()
        }
    }
}
