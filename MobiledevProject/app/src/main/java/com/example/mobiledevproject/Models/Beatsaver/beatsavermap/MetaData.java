package com.example.mobiledevproject.Models.Beatsaver.beatsavermap;

import java.util.List;

public class MetaData {

    private Difficulties difficulties;
    private int duration;
    private boolean automapper;

    private List<Characteristics> characteristics;

    public Difficulties getDifficulties() {
        return difficulties;
    }

    public int getDuration() {
        return duration;
    }

    public boolean isAutomapper() {
        return automapper;
    }

    public List<Characteristics> getCharacteristics() {
        return characteristics;
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

    private String levelAuthorName;
    private String songAuthorName;
    private String songName;
    private String songSubName;
    private Double bpm;
}
