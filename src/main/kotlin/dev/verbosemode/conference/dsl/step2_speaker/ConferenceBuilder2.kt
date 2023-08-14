package dev.verbosemode.conference.dsl.step1_basic_properties

import dev.verbosemode.conference.api.Conference
import dev.verbosemode.conference.api.Speaker

class ConferenceBuilderWithSpeaker {
    var name: String = ""
    var organizerContact: String = ""
    private val speaker = mutableListOf<Speaker>()

    fun speaker(init: SpeakerListBuilder.() -> Unit) {
        val dsl = SpeakerListBuilder()
        dsl.init()
        speaker.addAll(dsl.build())
    }

    fun build(): Conference {
        val conference = Conference(name, organizerContact)
        conference.speakers.addAll(speaker)
        return conference
    }
}

class SpeakerListBuilder {
    private val speaker = mutableListOf<Speaker>()

    fun build(): List<Speaker> {
        return speaker
    }

    fun book(init: SpeakerBuilder.() -> Unit) {
        val dsl = SpeakerBuilder()
        dsl.init()
        speaker.add(dsl.build())
    }

    class SpeakerBuilder {
        var name: String = ""
        var bio: String = ""
        var expertise = mutableListOf<String>()

        fun build(): Speaker {
            return Speaker(name, bio, expertise)
        }
    }
}

fun conference2(init: ConferenceBuilderWithSpeaker.() -> Unit): Conference {
    val dsl = ConferenceBuilderWithSpeaker()
    dsl.init()
    return dsl.build()
}