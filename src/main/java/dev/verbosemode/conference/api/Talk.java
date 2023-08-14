package dev.verbosemode.conference.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Talk {
    private String title;
    private final List<Speaker> speakers = new ArrayList<>();
    private String startTime;
    private List<String> audience;

    public Talk(String title, String startTime, List<String> audience) {
        this.title = title;
        this.startTime = startTime;
        this.audience = audience;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Speaker> getSpeakers() {
        return speakers;
    }

    public void addSpeaker(Speaker speaker) {
        this.speakers.add(speaker);
    }


    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public List<String> getAudience() {
        return audience;
    }

    public void setAudience(List<String> audience) {
        this.audience = audience;
    }

    @Override
    public String toString() {
        return "Talk{" +
                "title='" + title + '\'' +
                ", speakers=" + speakers +
                ", startTime='" + startTime + '\'' +
                ", audience=" + audience +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Talk talk = (Talk) o;
        return Objects.equals(title, talk.title) && Objects.equals(speakers, talk.speakers)
                && Objects.equals(startTime, talk.startTime) && Objects.equals(audience, talk.audience);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, speakers, startTime, audience);
    }
}
