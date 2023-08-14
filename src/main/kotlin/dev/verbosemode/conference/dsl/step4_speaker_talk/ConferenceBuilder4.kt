package dev.verbosemode.conference.dsl.step4_speaker_talk

import dev.verbosemode.conference.api.Conference
import dev.verbosemode.conference.api.Speaker
import dev.verbosemode.conference.api.Talk

class ConferenceBuilderComplete {
    var name: String = ""
    var organizerContact: String = ""
    private val speaker = mutableListOf<Speaker>()
    private val talks = mutableListOf<Talk>()
    private val speakerSymbolTable = mutableMapOf<String, Speaker>()

    fun speaker(init: SpeakerListBuilder2.() -> Unit) {
        val dsl = SpeakerListBuilder2()
        dsl.init()
        val bookedSpeaker = dsl.build()
        speakerSymbolTable.putAll(bookedSpeaker.map { it.name to it })
        speaker.addAll(bookedSpeaker)
    }

    fun talks(init: TaskListBuilder1.() -> Unit) {
        val dsl = TaskListBuilder1(speakerSymbolTable)
        dsl.init()

    }

    fun build(): Conference {
        val conference = Conference(name, organizerContact)
        conference.speakers.addAll(speaker)
        return conference
    }
}

class TaskListBuilder1(private val speakerSymbolTable: Map<String, Speaker>) {

    private val talks = mutableListOf<Talk>()

    fun schedule(init: TalkBuilder.() -> Unit) {
        val dsl = TalkBuilder(speakerSymbolTable)
        dsl.init()
        talks.add(dsl.build())
    }


    class TalkBuilder(private val speakerSymbolTable: Map<String, Speaker>) {
        var title: String = ""
        var description: String = ""
        var startTime: String = ""
        private val speaker = mutableSetOf<String>()
        private val audience = mutableListOf<String>()

        fun build(): Talk {
            val talk = Talk(title, description, audience)
            talk.startTime = startTime
            require(speaker.isNotEmpty()) { "Talk must have at least one speaker" }
            require(speakerSymbolTable.keys.containsAll(speaker)) { "Speaker must be booked before scheduling talk" }
            talk.speakers.addAll(speakerSymbolTable.filterKeys { speaker.contains(it) }.values)
            return talk
        }

        class SpeakerNameListBuilder {
            val speaker = mutableListOf<String>()
            operator fun String.unaryPlus() {
                speaker.add(this)
            }
        }


        fun speaker(init: SpeakerNameListBuilder.() -> Unit) {
            val dsl = SpeakerNameListBuilder()
            dsl.init()
            speaker.addAll(dsl.speaker)
        }
    }

}

class SpeakerListBuilder2 {
    private val speaker = mutableListOf<Speaker>()

    fun build(): List<Speaker> {
        return speaker
    }

    fun book(init: SpeakerBuilder2.() -> Unit) {
        val dsl = SpeakerBuilder2()
        dsl.init()
        speaker.add(dsl.build())
    }

    class SpeakerBuilder2 {
        var name: String = ""
        var bio: String = ""
        var expertise = mutableListOf<String>()

        fun build(): Speaker {
            return Speaker(name, bio, expertise)
        }

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
    }
}

fun conference4(init: ConferenceBuilderComplete.() -> Unit): Conference {
    val dsl = ConferenceBuilderComplete()
    dsl.init()
    return dsl.build()
}