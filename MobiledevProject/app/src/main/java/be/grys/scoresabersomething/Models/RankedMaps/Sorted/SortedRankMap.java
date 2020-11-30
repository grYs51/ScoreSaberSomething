package be.grys.scoresabersomething.Models.RankedMaps.Sorted;

import com.google.gson.annotations.SerializedName;

public class SortedRankMap {
    private String id;
    private String name;
    private String artist;
    private String mapper;
    private int bpm;
    private String beatSaverKey;
    private int downloads;
    private int upvotes;
    private int downvotes;
    private double rating;
    private String download;
    private double duration;

    @SerializedName("_id")
    private String ids;

    private SortedDiffs sortedDiffs;
}
