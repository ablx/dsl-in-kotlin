package dev.verbosemode.conference.dsl.step2_speaker

import dev.verbosemode.conference.dsl.step1_basic_properties.conference2
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ConferenceBuilder2Test {

    @Test
    fun `should create a conference with name and contact`() {
        val conference = conference2 {
            name = "TechConf 2023"
            organizerContact = "organizer"
            speaker {
                book {
                    name = "John Doe"
                    bio = "Software engineer and tech enthusiast."
                    expertise = mutableListOf("Java", "Python")
                }
            }
        }

        assertEquals("TechConf 2023", conference.name)
        assertEquals("organizer", conference.organizerContact)
    }
}