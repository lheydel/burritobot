package com.burritobot.util

import com.burritobot.user.UserRepository
import dev.kord.common.entity.Snowflake
import dev.kord.core.entity.Message

suspend fun Message.sign(authorId: Snowflake?) {
    authorId?.let { id ->
        UserRepository.getById(id)?.let { user ->
            addReaction(user.signature.toReaction())
        }
    }
}
