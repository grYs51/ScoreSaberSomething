package com.example.mobiledevproject.Models.PlayerProfile;

import com.google.gson.annotations.SerializedName;

public class PlayerScoreStats {

    @SerializedName("totalScore")
    private long total_Score;

    @SerializedName("totalRankedScore")
    private long total_Ranked_Score;

    @SerializedName("averageRankedAccuracy")
    private Double average_ranked_Accuracy;

    @SerializedName("totalPlayCount")
    private int total_Play_Count;

    @SerializedName("rankedPlayCount")
    private int ranked_Play_Count;


    public long getTotal_Score() {
        return total_Score;
    }

    public long getTotal_Ranked_Score() {
        return total_Ranked_Score;
    }

    public double getAverage_ranked_Accuracy() {
        return average_ranked_Accuracy;
    }

    public int getTotal_Play_Count() {
        return total_Play_Count;
    }

    public int getRanked_Play_Count() {
        return ranked_Play_Count;
    }

}
