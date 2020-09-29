package com.example.mobiledevproject.profile.PlayerProfile;

import com.google.gson.annotations.SerializedName;

public class Player {

    @SerializedName("playerInfo")
    private PlayerPlayerInfo player_info;
    @SerializedName("scoreStats")
    private  PlayerScoreStats score_stats;

    public PlayerPlayerInfo getPlayer_info(){ return player_info;}
    public PlayerScoreStats getScore_stats(){ return score_stats;}
}

