package com.example.mobiledevproject.Models.Beatsaver.beatsavermap;

public class Stats {
    private int downloads;
    private int plays;
    private int downVotes;
    private int upVotes;

    public int getDownloads() {
        return downloads;
    }

    public int getPlays() {
        return plays;
    }

    public int getDownVotes() {
        return downVotes;
    }

    public int getUpVotes() {
        return upVotes;
    }

    public double getHeat() {
        return heat;
    }

    public double getRating() {
        return rating;
    }

    private double heat;
    private double rating;
}
