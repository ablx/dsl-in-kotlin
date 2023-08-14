package dev.verbosemode.conference.dsl.step5_final


import dev.verbosemode.conference.api.ConferenceUtil

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class ConferenceBuilderTest {

    @Test
    fun `should not be able to create a talk with a not existing speaker`() {
        val exception = assertThrows<IllegalArgumentException> {
            conference {
                name = "TechConf 2023"
                organizerContact = ""
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
        }
        assertEquals("Speaker 'John Doe' does not exist", exception.message)
    }


    @Test
    fun `should create a valid conference`() {
        val expected = ConferenceUtil.createConference()
        val actual = conference {
            name = "TechConf 2023"
            organizerContact = "info@techconf.com"

            talks {
                speaker {

                }
            }

            speaker {
                talks {

                }
                book {
                    name = "John Doe"
                    bio = "Software engineer and tech enthusiast."
                    expertise {
                        +"Java"
                        +"Python"
                    }
                }
                book {
                    name = "Jane Smith"
                    bio = "AI researcher and data science enthusiast."
                    expertise {
                        +"Machine Learning"
                        +"Natural Language Processing"
                    }
                }
            }
            talks {
                schedule {
                    title = "Introduction to Java"
                    startTime = "9:00 AM"
                    speaker {
                        +"John Doe"
                        +"Jane Smith"
                    }

                    audience mayBe "Developers"
                    audience {
                        may be "Developers"
                        may be "Java enthusiasts"
                    }
                    schedule {
                        title = "AI in Everyday Life"
                        startTime = "11:30 AM"
                        speaker {
                            +"Jane Smith"
                        }
                        audience { may be "General audience" }
                    }
                }

            }
        }

        assertEquals(expected.name, actual.name)
        assertEquals(expected.organizerContact, actual.organizerContact)
        assertContentEquals(expected.speakers, actual.speakers)
        assertContentEquals(expected.talks.sortedBy { it.title }, actual.talks.sortedBy { it.title })


    }


}