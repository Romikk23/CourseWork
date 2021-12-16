package music;
import java.time.Duration;

public class Track {
    private String name;
    private Duration duration;
    private String style;

    public Track(String name, Duration duration, String style) {
        this.name = name;
        this.duration = duration;
        this.style = style;
    }

    public String getName() {
        return this.name;
    }
    public Duration getDuration() {
        return this.duration;
    }
    public String getStyle() {
        return this.style;
    }
    public String toString() {
        return "Name: " + this.getName() + ", Duration: " + this.getDuration().toMinutesPart()+":"+ this.getDuration().toSecondsPart() + ", Style: " + this.getStyle();
    }
}
