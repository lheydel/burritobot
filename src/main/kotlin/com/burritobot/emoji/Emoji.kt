package com.burritobot.emoji

import dev.kord.common.entity.Snowflake
import dev.kord.core.entity.ReactionEmoji

sealed class Emoji(val name: String, val id: Long) {
    val formatted: String get() = "<:$name:$id>"

    fun toReaction(): ReactionEmoji.Custom = ReactionEmoji.Custom(Snowflake(id), name, isAnimated = false)

    // Burrito emojis
    data object BURRITAL : Emoji("burrital", 369550219173429259)
    data object BURRITOEL : Emoji("burritoel", 526907423160533012)

    // Yes/No/Maybe
    data object OUI : Emoji("oui", 593794204598272001)
    data object NON : Emoji("non", 595161673438855168)
    data object MAYBE : Emoji("maybe", 596709528280629267)

    // OK emojis
    data object OK1 : Emoji("OK1", 369541026404237312)
    data object OK2 : Emoji("OK2", 655871904548126741)

    // Misc
    data object PD : Emoji("PD", 513834875418312723)
    data object SAD : Emoji("sad", 369883690479648768)

    // User emojis
    data object CLOCHETTE : Emoji("Clochette", 369544749121798144)
    data object BRINDILLE : Emoji("brindille", 369549173189640192)
    data object BOB : Emoji("bob", 369882624681836544)

    // Numbers
    data object SIXSIXSIX : Emoji("666", 542832941064126464)
    data object NINENINENINE : Emoji("999", 539495256916361228)

    // Validation
    data object CHECK : Emoji("vu", 546821321984835591)
    data object VALID : Emoji("valid", 606111192121081867)
}
