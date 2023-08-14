package dev.verbosemode.conference.dsl.step5_no_dsl_marker

import dev.verbosemode.conference.api.Conference
import dev.verbosemode.conference.api.Speaker
import dev.verbosemode.conference.api.Talk
import dev.verbosemode.conference.dsl.step5_final.SpeakerListBuilderFinal
import dev.verbosemode.conference.dsl.step5_final.TalkListBuilder
import dev.verbosemode.conference.dsl.step5_final.may


internal class ConferenceBuilderNoMarker {
    var name: String = ""
    var organizerContact: String = ""
    private val speakers = mutableSetOf<Speaker>()
    private val speakerSymbolTable = mutableMapOf<String, Speaker>()
    private val talks = mutableSetOf<Talk>()

    fun build(): Conference {
        val conference = Conference(name, organizerContact)

        speakers.forEach {
            conference.addSpeaker(it)
        }
        talks.forEach {
            conference.addTalk(it)
        }
        return conference
    }


    fun talks(init: TalkListBuilder.() -> Unit) {
        val dsl = TalkListBuilder(speakerSymbolTable)
        dsl.init()
        talks.addAll(dsl.talks)
    }


    fun speakers(init: SpeakerListBuilderFinal.() -> Unit) {
        val dsl = SpeakerListBuilderFinal()
        dsl.init()
        speakers.addAll(dsl.speakers)
        speakerSymbolTable.putAll(dsl.speakers.map { it.name to it })
    }


}


class SpeakerListBuilderNoMarker {
    val speakers = mutableSetOf<Speaker>()


    class SpeakerBuilder {
        var name: String = ""
        var bio: String = ""
        val expertise = mutableListOf<String>()

        class ExpertiseBuilder {
            val expertise = mutableListOf<String>()
            operator fun String.unaryPlus() {
                expertise.add(this)
            }


        }

        fun expertise(init: ExpertiseBuilder.() -> Unit) {
            val dsl = ExpertiseBuilder()
            dsl.init()
            expertise.addAll(dsl.expertise)
        }

        fun build(): Speaker {
            return Speaker(name, bio, expertise)
        }
    }

    fun book(init: SpeakerBuilder.() -> Unit) {
        val dsl = SpeakerBuilder()
        dsl.init()
        speakers.add(dsl.build())
    }
}


class TalkListBuilderNoMarker(
    private val speakerSymbolTable: Map<String, Speaker>
) {
    val talks = mutableSetOf<Talk>()

    fun schedule(init: TalkBuilder.() -> Unit) {
        val dsl = TalkBuilder(speakerSymbolTable)
        dsl.init()
        talks.add(dsl.build())
    }


    class TalkBuilder(
        private val speakerSymbolTable: Map<String, Speaker>
    ) {
        var title: String = ""
        var startTime: String = ""
        private val speaker = mutableSetOf<Speaker>()
        private val audienceCollection = mutableListOf<String>()

        class SpeakerListBuilder {
            val speakerCollection = mutableListOf<String>()

            operator fun String.unaryPlus() {
                speakerCollection.add(this)
            }
        }

        val audience = this

        infix fun mayBe(audience: String) {
            audienceCollection.add(audience)
        }

        class AudienceListBuilder {
            val audienceCollection = mutableListOf<String>()

            infix fun may.be(name: String) {
                audienceCollection.add(name)
            }
        }

        fun speaker(init: SpeakerListBuilder.() -> Unit) {
            val dsl = SpeakerListBuilder()
            dsl.init()
            val resolvedSpeaker = dsl.speakerCollection.map {
                speakerSymbolTable[it] ?: throw IllegalArgumentException("Speaker '$it' does not exist")
            }
            speaker.addAll(resolvedSpeaker)
        }

        fun audience(init: AudienceListBuilder.() -> Unit) {
            val dsl = AudienceListBuilder()
            dsl.init()
            audienceCollection.addAll(dsl.audienceCollection)
        }


        fun build(): Talk {
            val talk = Talk(title, startTime, audienceCollection)
            talk.speakers.addAll(speaker)
            return talk
        }

    }


}


internal fun conferenceNoMarker(init: ConferenceBuilderNoMarker.() -> Unit): Conference {
    val dsl = ConferenceBuilderNoMarker()
    dsl.init()
    return dsl.build()
}