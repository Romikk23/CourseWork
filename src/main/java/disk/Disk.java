package disk;

import music.Track;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Disk {
    private static List<Track> tracklist = new ArrayList();
    private static Duration durationFrom = Duration.ofSeconds(120);
    private static Duration durationTo = Duration.ofSeconds(250);

    public static void setDurationFrom(Duration durationFrom) {
        Disk.durationFrom = durationFrom;
    }
    public static void setDurationTo(Duration durationTo) {
        Disk.durationTo = durationTo;
    }
    public static List<Track> getTracklist() {
        return tracklist;
    }
    public static Track getTrackOnDisk(Integer index) {
        return tracklist.get(index);
    }

    public static Duration getDurationFrom() {
        return durationFrom;
    }

    public static Duration getDurationTo() {
        return durationTo;
    }

    public static void addRecordsOnDisk(Track track){
        if(tracklist.contains(track)){
            System.out.println("This record already exist ");
        } else {
            tracklist.add(track);
        }
    }
    public static void deleteRecordsFromDisk(Track track){
        if(tracklist.contains(track)){
            tracklist.remove(track);
        } else {
            System.out.println("This record isn't exist ");

        }
    }
}
