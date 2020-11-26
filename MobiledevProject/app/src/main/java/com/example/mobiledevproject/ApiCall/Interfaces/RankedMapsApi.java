package com.example.mobiledevproject.ApiCall.Interfaces;

import com.example.mobiledevproject.Models.PlayerProfile.Player;
import com.example.mobiledevproject.Models.RankedMaps.RankedMap;
import com.example.mobiledevproject.Models.RankedMaps.RankedMapsList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RankedMapsApi {

    @GET("api.php")
    Call<RankedMapsList> getRankedMaps(@Query("function") String function, @Query("cat") String cat, @Query("page") String page, @Query("limit") String limit);
}
