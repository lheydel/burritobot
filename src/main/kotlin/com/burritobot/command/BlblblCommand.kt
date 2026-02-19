package com.burritobot.command

import dev.kord.core.entity.Message

class BlblblCommand : Command {
    override val name = "blblbl"
    override val description = "Say burrito catchphrase"

    override suspend fun execute(message: Message, args: List<String>) {
        message.delete()
        message.channel.createMessage("I'm a burritooooo!")
    }
}
