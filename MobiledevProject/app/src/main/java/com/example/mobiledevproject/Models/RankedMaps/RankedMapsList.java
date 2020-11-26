package com.example.mobiledevproject.Models.RankedMaps;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RankedMapsList {

    @SerializedName("songs")
    private List<RankedMap> rankedMaps;

    public List<RankedMap> getRankedMaps() {
        return rankedMaps;
    }
}
