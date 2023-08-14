package dev.verbosemode.conference.dsl.step1_basic_properties

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ConferenceBuilder1Test {

    @Test
    fun `should create a conference with name and contact`() {
        val conference = conference1 {
            name = "TechConf 2023"
            organizerContact = "organizer"
        }

        assertEquals("TechConf 2023", conference.name)
        assertEquals("organizer", conference.organizerContact)
    }
}