package com.burritobot.utils

import kotlin.random.Random

fun chance(probability: Double): Boolean = Random.nextDouble() < probability
