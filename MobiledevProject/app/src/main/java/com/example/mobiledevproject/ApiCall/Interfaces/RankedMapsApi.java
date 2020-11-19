package com.example.mobiledevproject.ApiCall.Interfaces;

import com.example.mobiledevproject.Models.PlayerProfile.Player;
import com.example.mobiledevproject.Models.RankedMaps.RankedMapsList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RankedMapsApi {

    @GET("api.php?function=get-leaderboards&cat={cat}&page={page}&limit={limit}")
    Call<RankedMapsList> getRankedMaps(@Path(value = "cat", encoded = true) String cat, @Path(value = "page", encoded = true) String page, @Path(value = "limit", encoded = true) String limit);

}
