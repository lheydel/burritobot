package com.burritobot.command

import com.burritobot.insult.InsultService
import com.burritobot.user.UserRepository
import com.burritobot.util.sign
import dev.kord.core.entity.Message

class InsultCommand(
    private val insultService: InsultService
) : Command {
    override val name = "insult"
    override val description = "Insult someone (or compliment bots)"

    override suspend fun execute(message: Message, args: List<String>) {
        message.delete()
        val target = args.joinToString(" ")
        val text = if (UserRepository.isBot(target)) {
            insultService.getCompliment()
        } else {
            insultService.getInsult()
        }
        val textToSend = text.replaceFirstChar { it.lowercase() }

        val insultMessage = message.channel.createMessage("$target, $textToSend")
        insultMessage.sign(message.author?.id)
    }
}
