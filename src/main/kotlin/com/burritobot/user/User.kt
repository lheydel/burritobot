package com.burritobot.user

import com.burritobot.emoji.Emoji

data class User(
    val id: Long,
    val signature: Emoji,
    val isBot: Boolean = false,
) {
    val mention: String get() = "<@$id>"
}
