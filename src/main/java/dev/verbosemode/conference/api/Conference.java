package dev.verbosemode.conference.api;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class Conference {
    private List<Speaker> speakers;
    private List<Talk> talks;
    private String name;
    private String organizerContact;

    public Conference(String name, String organizerContact) {
        this.name = name;
        this.organizerContact = organizerContact;
        speakers = new ArrayList<>();
        talks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrganizerContact() {
        return organizerContact;
    }

    public void setOrganizerContact(String organizerContact) {
        this.organizerContact = organizerContact;
    }

    public List<Speaker> getSpeakers() {
        return speakers;
    }

    public List<Talk> getTalks() {
        return talks;
    }



    public void addSpeaker(Speaker speaker) {
        speakers.add(speaker);
    }

    public void addTalk(Talk talk) {
        talks.add(talk);
    }


    @Override
    public String toString() {
        return new StringJoiner(", ", Conference.class.getSimpleName() + "[", "]")
                .add("speakers=" + speakers)
                .add("talks=" + talks)
                .add("name='" + name + "'")
                .add("organizerContact='" + organizerContact + "'")
                .toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Conference) {
            Conference other = (Conference) obj;
            return this.name.equals(other.name) &&
                    this.organizerContact.equals(other.organizerContact) &&
                    this.speakers.equals(other.speakers) &&
                    this.talks.equals(other.talks);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return name.hashCode() + organizerContact.hashCode() + speakers.hashCode() +
                talks.hashCode();
    }
}
