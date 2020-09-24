package com.example.mobiledevproject.model.PlayerProfile;

import com.google.gson.annotations.SerializedName;

public class PlayerScoreStats {

    @SerializedName("totalScore")
    private Integer total_Score;

    @SerializedName("totalRankedScore")
    private Integer total_Ranked_Score;

    @SerializedName("averageRankedAccuracy")
    private Double average_ranked_Accuracy;

    @SerializedName("totalPlayCount")
    private Integer total_Play_Count;

    @SerializedName("rankedPlayCount")
    private Integer ranked_Play_Count;

    public Integer getTotal_Score() {
        return total_Score;
    }

    public Integer getTotal_Ranked_Score() {
        return total_Ranked_Score;
    }

    public double getAverage_ranked_Accuracy() {
        return average_ranked_Accuracy;
    }

    public Integer getTotal_Play_Count() {
        return total_Play_Count;
    }

    public Integer getRanked_Play_Count() {
        return ranked_Play_Count;
    }

}
