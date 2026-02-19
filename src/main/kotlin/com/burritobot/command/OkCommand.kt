package com.burritobot.command

import com.burritobot.model.Emoji
import dev.kord.core.entity.Message

class OkCommand : Command {
    override val name = "ok"
    override val description = "Send OK emoji"

    override suspend fun execute(message: Message, args: List<String>) {
        message.delete()
        message.channel.createMessage(Emoji.OK1.formatted)
    }
}
