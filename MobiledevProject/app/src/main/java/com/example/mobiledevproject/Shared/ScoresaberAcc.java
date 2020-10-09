package com.example.mobiledevproject.Shared;

import android.util.Log;

import static android.content.ContentValues.TAG;

public class ScoresaberAcc {



    public double getAcc(double score, int maxScore){
        return (score/maxScore)*100;
    }
}
