package disk;

import music.Track;

import java.util.ArrayList;
import java.util.List;

public class DiskUtils {
    public static List<Track> findRecordsByDuration(){
        List<Track> result = new ArrayList();
        if(Disk.getTracklist().isEmpty()){
            System.out.println("\nDisk is empty, add some records to your disk :)");
            return result;
        }
        int i = 1;
        for (Track track:Disk.getTracklist()) {
            if(track.getDuration().compareTo(Disk.getDurationFrom()) >= 0 && track.getDuration().compareTo(Disk.getDurationTo()) <= 0){
                result.add(track);
                System.out.printf("%03d   %s\n",i ,track);
            }
            i++;
        }
        return result;
    }
    public static List<Track> sortRecordsByGenre(String genre){
        List<Track> sortedByGenreTrackList = Disk.getTracklist();
        List<Track> result = new ArrayList();

        sortedByGenreTrackList.sort((r1, r2) -> r1.getStyle().compareTo(r2.getStyle()));
        for (Track track:sortedByGenreTrackList) {
            if(track.getStyle().equals(genre)){
                result.add(track);
            }
        }
        for (Track track:sortedByGenreTrackList) {
            if(!track.getStyle().equals(genre)){
                result.add(track);
            }
        }
        //tracklist = result;
        return result;
    }
}
