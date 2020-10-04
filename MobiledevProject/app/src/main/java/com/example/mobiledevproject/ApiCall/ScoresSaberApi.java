package com.example.mobiledevproject.ApiCall;

import com.example.mobiledevproject.Models.TopSongs;
import com.example.mobiledevproject.profile.PlayerProfile.Player;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ScoresSaberApi {

    @GET("player/{player_id}/full")
    Call<Player> getUserInfo(@Path(value = "player_id", encoded = true) String playerId);

    @GET("player/{player_id}/scores/top")
    Call<TopSongs> getTopSongs(@Path(value = "player_id", encoded = true) String playerId);
}
