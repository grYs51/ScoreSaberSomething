package be.grys.scoresabersomething.Models.Beatsaver.beatsavermap;

import java.io.Serializable;

public class Stats implements Serializable {

    private int plays;
    private int downloads;
    private int upVotes;
    private int downVotes;
    private double score;



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

    public double getScore() { return score; }
}
