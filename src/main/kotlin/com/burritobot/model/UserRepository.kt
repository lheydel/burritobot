package com.burritobot.model

import dev.kord.common.entity.Snowflake

object UserRepository {
    private val HYRLOS = User(168333966716174337, Emoji.CLOCHETTE)
    private val LOUEC = User(160483264761430016, Emoji.BRINDILLE)
    private val MROHMS = User(366581141118910464, Emoji.BOB)
    private val BOT_SOLO = User(726715798676176947, Emoji.BURRITAL, isBot = true)
    private val BOT_BTEAM = User(726765421495320631, Emoji.BURRITAL, isBot = true)

    private val users = listOf(HYRLOS, LOUEC, MROHMS, BOT_SOLO, BOT_BTEAM)
    private val botIds = users.filter { it.isBot }.map { it.id }

    fun getById(id: Snowflake): User? = users.find { Snowflake(it.id) == id }

    fun getById(id: Long): User? = users.find { it.id == id }

    fun isBot(mention: String): Boolean {
        return users.any { it.mention == mention && it.id in botIds } ||
                mention in listOf("burritobot", "bot")
    }
}
