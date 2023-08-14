package dev.verbosemode.conference.dsl.step1_basic_properties

import dev.verbosemode.conference.api.Conference

class ConferenceBuilder1 {
    var name: String = ""
    var organizerContact: String = ""

    fun build(): Conference {
        return Conference(name, organizerContact)
    }
}

fun conference1(init: ConferenceBuilder1.() -> Unit): Conference {
    val dsl = ConferenceBuilder1()
    dsl.init()
    return dsl.build()
}