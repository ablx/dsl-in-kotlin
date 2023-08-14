package dev.verbosemode.conference.dsl.step4_speaker_talk

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ConferenceBuilder4Test {

    @Test
    fun `should create a conference with name and contact`() {
        val conference = conference4 {
            name = "TechConf 2023"
            organizerContact = "organizer"
            speaker {
                book {
                    name = "John Doe"
                    bio = "Software engineer and tech enthusiast."
                    expertise {
                        +"Java"
                        +"Python"
                    }
                }
            }
            talks {
                schedule {
                    title = "Introduction to Java"
                    startTime = "9:00 AM"
                    speaker {
                        +"John Doe"
                    }
                }
            }
        }

        assertEquals("TechConf 2023", conference.name)
        assertEquals("organizer", conference.organizerContact)
        assertEquals(1, conference.speakers.size)
        assertEquals("John Doe", conference.speakers[0].name)
        assertEquals("Software engineer and tech enthusiast.", conference.speakers[0].bio)
        assertEquals(2, conference.speakers[0].expertise.size)
        assertEquals("Java", conference.speakers[0].expertise[0])
        assertEquals("Python", conference.speakers[0].expertise[1])
        assertEquals(1, conference.talks.size)
        assertEquals("Introduction to Java", conference.talks[0].title)
        assertEquals("9:00 AM", conference.talks[0].startTime)

    }
}