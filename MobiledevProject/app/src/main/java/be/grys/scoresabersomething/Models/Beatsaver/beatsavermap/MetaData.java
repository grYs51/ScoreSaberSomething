package be.grys.scoresabersomething.Models.Beatsaver.beatsavermap;

import java.io.Serializable;
import java.util.List;

public class MetaData implements Serializable {

    private Double bpm;
    private int duration;
    private String songName;
    private String songSubName;
    private String songAuthorName;
    private String levelAuthorName;


    public int getDuration() {
        return duration;
    }

    public String getLevelAuthorName() {
        return levelAuthorName;
    }

    public String getSongAuthorName() {
        return songAuthorName;
    }

    public String getSongName() {
        return songName;
    }

    public String getSongSubName() {
        return songSubName;
    }

    public Double getBpm() {
        return bpm;
    }

}
