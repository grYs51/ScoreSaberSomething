package com.example.mobiledevproject.model.PlayerProfile;

import com.example.mobiledevproject.model.PlayerProfile.Player;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ScoresSaberApi {

    @GET("player/{player_id}/full")
    Call<Player> getUserInfo(@Path(value = "player_id", encoded = true) String playerId);

}
