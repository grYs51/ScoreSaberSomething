package com.example.mobiledevproject.Shared;

public class ScoresaberAcc {


    public double getAcc(double score, int maxScore) {
        return (score / maxScore) * 100;
    }
}
