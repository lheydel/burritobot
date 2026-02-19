package com.burritobot.util

import java.text.Normalizer

fun String.unaccent(): String = Normalizer.normalize(this, Normalizer.Form.NFD)
        .replace(Regex("\\p{InCombiningDiacriticalMarks}+"), "")

fun String.normalize(): String = this.lowercase().unaccent()
