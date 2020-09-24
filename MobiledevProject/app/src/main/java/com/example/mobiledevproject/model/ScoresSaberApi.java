package com.example.mobiledevproject.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ScoresSaberApi {

    @GET("player/{player_id}/full")
    Call<Player> getUserInfo(@Path(value = "player_id", encoded = true) String playerId);

}
