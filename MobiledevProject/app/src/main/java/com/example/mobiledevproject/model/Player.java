package com.example.mobiledevproject.model;

import com.google.gson.annotations.SerializedName;

public class Player {

    @SerializedName("playerInfo")
    private  Object player_info;
    @SerializedName("scoreStats")
    private  Object score_stats;

    public Object getPlayer_info(){ return player_info;}
    public Object getScore_stats(){ return score_stats;}

}
