package com.burritobot.util

import kotlin.random.Random

fun chance(probability: Double): Boolean = Random.nextDouble() < probability
