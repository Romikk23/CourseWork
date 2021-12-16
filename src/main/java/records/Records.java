package records;

import music.Track;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Records {
    private static List<Track> tracklist = new ArrayList();

    public Records() {
        this.tracklist.add(new Track("Adele - Easy On Me ", Duration.ofSeconds(250), "Pop"));
        this.tracklist.add(new Track("Smells Like Teen Spirit - Nirvana ", Duration.ofSeconds(280), "Rock"));
        this.tracklist.add(new Track("Imagine - John Lennon ", Duration.ofSeconds(210), "Hip Hop"));
        this.tracklist.add(new Track("One - U2 ", Duration.ofSeconds(290), "Pop"));
        this.tracklist.add(new Track("Billie Jean - Michael Jackson ", Duration.ofSeconds(190), "Rock"));
        this.tracklist.add(new Track("Bohemian Rhapsody - Queen ", Duration.ofSeconds(150), "Hip Hop"));
        this.tracklist.add(new Track("Hey Jude - The Beatles ", Duration.ofSeconds(310), "Classis"));
        this.tracklist.add(new Track("Like A Rolling Stone - Bob Dylan ", Duration.ofSeconds(250), "Rap"));
        this.tracklist.add(new Track("I Can't Get No Satisfaction - Rolling Stones ", Duration.ofSeconds(189), "Pop"));
        this.tracklist.add(new Track("God Save The Queen - Sex Pistols ", Duration.ofSeconds(225), "Rap"));
        this.tracklist.add(new Track("Sweet Child O'Mine - Guns N' Roses ", Duration.ofSeconds(320), "Hip Hop"));
        this.tracklist.add(new Track("London Calling - The Clash ", Duration.ofSeconds(350), "Rock"));
    }

    public static List<Track> getTracklist() {
        return tracklist;
    }
    public static Track getTrack(Integer index) {
        return tracklist.get(index);
    }
    public static void AddTrack(Track track) {
        tracklist.add(track);
    }

}
