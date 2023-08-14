package dev.verbosemode.conference.api;

import java.util.List;

public class ConferenceUtil {

    public static Conference createConference() {
        // Create a conference
        Conference conference = new Conference("TechConf 2023", "info@techconf.com");

        // Create speakers
        Speaker speaker1 = new Speaker("John Doe", "Software engineer and tech enthusiast.",
                List.of("Java", "Python"));
        Speaker speaker2 = new Speaker("Jane Smith", "AI researcher and data science enthusiast.",
                List.of("Machine Learning", "Natural Language Processing"));


        // Create talks
        Talk talk1 = new Talk("Introduction to Java", "9:00 AM",
                List.of("Developers", "Java enthusiasts"));
        talk1.addSpeaker(speaker1);
        talk1.addSpeaker(speaker2);


        Talk talk2 = new Talk("AI in Everyday Life", "11:30 AM",
                List.of("General audience"));
        talk2.addSpeaker(speaker2);

        // Add speakers, rooms, and talks to the conference
        conference.addSpeaker(speaker1);
        conference.addSpeaker(speaker2);
        conference.addTalk(talk1);
        conference.addTalk(talk2);
        return conference;
    }
}
