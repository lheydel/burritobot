package com.burritobot.service

class InsultService {
    private val insults = loadResource("/insults/insults.txt")
    private val compliments = loadResource("/insults/compliments.txt")

    fun getInsult(): String = insults.random()

    fun getCompliment(): String = compliments.random()

    private fun loadResource(path: String): List<String> {
        return javaClass.getResourceAsStream(path)
            ?.bufferedReader()
            ?.readLines()
            ?.filter { it.isNotBlank() }
            ?: emptyList()
    }
}
