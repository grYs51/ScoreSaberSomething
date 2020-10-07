package com.example.mobiledevproject.Models.Beatsaver;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MapsBeatsaver {

    @SerializedName("docs")
    private List<BeatsaverMap> beatsaverMaps;
    @SerializedName("totalDocs")
    private int total_docs;
    @SerializedName("lastPage")
    private int lastPage;
    @SerializedName("prevPage")
    private int prevPage;
    @SerializedName("nextPage")
    private int nextPage;




}
