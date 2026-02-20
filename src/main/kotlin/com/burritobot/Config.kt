package com.burritobot

import dev.kord.common.entity.Snowflake

object Config {
    val botToken: String by lazy { getRequiredEnv("BOT_TOKEN") }
    val tenorToken: String by lazy { getRequiredEnv("TENOR_TOKEN") }
    val guildIds: List<Snowflake> by lazy {
        getRequiredEnv("GUILD_IDS")
            .split(",")
            .map { Snowflake(it.trim()) }
    }

    private fun getRequiredEnv(name: String): String {
        return System.getenv(name)
            ?: error("$name environment variable is required")
    }
}
