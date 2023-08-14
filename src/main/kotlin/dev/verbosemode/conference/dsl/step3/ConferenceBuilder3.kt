package dev.verbosemode.conference.dsl.step3

import dev.verbosemode.conference.api.Conference
import dev.verbosemode.conference.api.Speaker
import dev.verbosemode.conference.dsl.step4_speaker_talk.SpeakerListBuilder2

class ConferenceBuilderWithSpeaker2 {
    var name: String = ""
    var organizerContact: String = ""
    private val speaker = mutableListOf<Speaker>()

    fun speaker(init: SpeakerListBuilder2.() -> Unit) {
        val dsl = SpeakerListBuilder2()
        dsl.init()
        speaker.addAll(dsl.build())
    }

    fun build(): Conference {
        val conference = Conference(name, organizerContact)
        conference.speakers.addAll(speaker)
        return conference
    }
}


fun conference3(init: ConferenceBuilderWithSpeaker2.() -> Unit): Conference {
    val dsl = ConferenceBuilderWithSpeaker2()
    dsl.init()
    return dsl.build()
}