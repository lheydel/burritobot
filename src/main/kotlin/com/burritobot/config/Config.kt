package com.burritobot.config

object Config {
    val botToken: String by lazy { getRequiredEnv("BOT_TOKEN") }
    val tenorToken: String by lazy { getRequiredEnv("TENOR_TOKEN") }

    private fun getRequiredEnv(name: String): String {
        return System.getenv(name)
            ?: error("$name environment variable is required")
    }
}
