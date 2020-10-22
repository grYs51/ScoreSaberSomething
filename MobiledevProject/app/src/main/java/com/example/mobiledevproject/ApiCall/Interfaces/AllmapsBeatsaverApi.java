package com.example.mobiledevproject.ApiCall.Interfaces;

import com.example.mobiledevproject.Models.Beatsaver.MapsBeatsaver;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface AllmapsBeatsaverApi {

    @Headers({
            "User-Agent: MobileDevApp\\1.0.0"
    })

    @GET("api/maps/{sorting}/{page}")
    Call<MapsBeatsaver> getMaps(@Path(value = "sorting", encoded = true) String sorting, @Path(value = "page", encoded = true) String page);
}
