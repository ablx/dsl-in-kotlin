package dev.verbosemode.conference.api;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConferenceTest {

    @Test
    public void testCompleteConferenceConfiguration() {
        // Create a conference
        Conference conference =  ConferenceUtil.createConference();

        // Verify the conference configuration
        assertEquals("TechConf 2023", conference.getName());
        assertEquals("info@techconf.com", conference.getOrganizerContact());

        assertEquals(2, conference.getSpeakers().size());
        assertEquals(2, conference.getTalks().size());

        assertEquals("John Doe", conference.getSpeakers().get(0).getName());
        assertEquals("Software engineer and tech enthusiast.", conference.getSpeakers().get(0).getBio());
        assertEquals("Jane Smith", conference.getSpeakers().get(1).getName());
        assertEquals("AI researcher and data science enthusiast.", conference.getSpeakers().get(1).getBio());


        assertEquals("Introduction to Java", conference.getTalks().get(0).getTitle());
        assertTrue(conference.getTalks().get(0).getAudience().contains("Developers"));
        assertTrue(conference.getTalks().get(0).getAudience().contains("Java enthusiasts"));
        assertEquals("John Doe", conference.getTalks().get(0).getSpeakers().get(0).getName());
        assertEquals("Jane Smith", conference.getTalks().get(0).getSpeakers().get(1).getName());


        assertEquals("AI in Everyday Life", conference.getTalks().get(1).getTitle());
        assertEquals("11:30 AM", conference.getTalks().get(1).getStartTime());
        assertTrue(conference.getTalks().get(1).getAudience().contains("General audience"));
       assertEquals("Jane Smith", conference.getTalks().get(1).getSpeakers().get(0).getName());
    }


}
