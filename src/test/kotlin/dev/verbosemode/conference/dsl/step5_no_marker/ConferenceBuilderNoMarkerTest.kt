package dev.verbosemode.conference.dsl.step5_no_marker


import dev.verbosemode.conference.dsl.step5_no_dsl_marker.conferenceNoMarker
import org.junit.jupiter.api.Test

class ConferenceBuilderNoMarkerTest {


    @Test
    fun `should create a valid conference`() {
        conferenceNoMarker {
            speakers {
                talks { }
            }
            talks {
                speakers {
                    speakers {
                        talks { }
                    }
                }
            }
        }

    }


}