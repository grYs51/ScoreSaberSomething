package com.example.mobiledevproject.ApiCall.Interfaces;
import com.example.mobiledevproject.Models.Scores;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TopSongApi {

    @GET("api/player/{player_id}/scores/top/{page}")
    Call<Scores> getTopSongs(@Path(value = "player_id", encoded = true) String playerId, @Path(value = "page",encoded = true) String page);
}
