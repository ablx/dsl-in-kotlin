package dev.verbosemode.conference.api;

import java.util.List;
import java.util.Objects;

public class Speaker {
    private String name;
    private String bio;
    private List<String> expertise;

    public Speaker(String name, String bio, List<String> expertise) {
        this.name = name;
        this.bio = bio;
        this.expertise = expertise;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<String> getExpertise() {
        return expertise;
    }

    public void setExpertise(List<String> expertise) {
        this.expertise = expertise;
    }

    @Override
    public String toString() {
        return "Speaker{" +
                "name='" + name + '\'' +
                ", bio='" + bio + '\'' +
                ", expertise=" + expertise +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Speaker speaker = (Speaker) o;
        return Objects.equals(name, speaker.name) && Objects.equals(bio, speaker.bio) && Objects.equals(expertise, speaker.expertise);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, bio, expertise);
    }
}
