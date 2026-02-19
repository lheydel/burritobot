package com.burritobot.model

data class User(
    val id: Long,
    val signature: Emoji,
    val isBot: Boolean = false,
) {
    val mention: String get() = "<@$id>"
}
