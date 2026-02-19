package com.burritobot.command

import com.burritobot.model.Emoji
import dev.kord.core.entity.Message

class PdCommand : Command {
    override val name = "pd"
    override val description = "Send PD emoji"

    override suspend fun execute(message: Message, args: List<String>) {
        message.delete()
        message.channel.createMessage(Emoji.PD.formatted)
    }
}
