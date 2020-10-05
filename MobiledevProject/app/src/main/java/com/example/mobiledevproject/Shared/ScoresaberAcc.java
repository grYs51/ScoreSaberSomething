package com.example.mobiledevproject.Shared;

import android.util.Log;

import static android.content.ContentValues.TAG;

public class ScoresaberAcc {
    private double score;
    private double maxScore;

    public ScoresaberAcc(int score, int maxScore){
        this.maxScore = maxScore;
        this.score = score;
    }

    public double getAcc(){
        return (score/maxScore)*100;
    }
}
