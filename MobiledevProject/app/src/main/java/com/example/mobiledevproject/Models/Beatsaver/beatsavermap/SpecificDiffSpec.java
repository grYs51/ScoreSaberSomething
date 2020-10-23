package com.example.mobiledevproject.Models.Beatsaver.beatsavermap;

import java.io.Serializable;

public class SpecificDiffSpec implements Serializable {


    public double getDuration() {
        return duration;
    }

    public int getLength() {
        return length;
    }

    public int getBombs() {
        return bombs;
    }

    public int getNotes() {
        return notes;
    }

    public int getObstacles() {
        return obstacles;
    }

    public double getNjs() {
        return njs;
    }

    public double getNjsOffset() {
        return njsOffset;
    }

    private double duration;
    private int length;
    private int bombs;
    private int notes;
    private int obstacles;
    private double njs;
    private double njsOffset;
}
