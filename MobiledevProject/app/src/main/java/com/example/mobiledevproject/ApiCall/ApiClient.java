package com.example.mobiledevproject.ApiCall;

import com.example.mobiledevproject.ApiCall.Interfaces.AllmapsBeatsaverApi;
import com.example.mobiledevproject.ApiCall.Interfaces.PlayerFullApi;
import com.example.mobiledevproject.ApiCall.Interfaces.RecentSongApi;
import com.example.mobiledevproject.ApiCall.Interfaces.TopSongApi;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    String scoresaber = "https://new.scoresaber.com/";
    String beatsaver = "https://beatsaver.com/";

    private static Retrofit getRetrofit(String baseUrl){

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();


        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    public static PlayerFullApi getPlayerService(){

        return getRetrofit( "https://new.scoresaber.com/").create(PlayerFullApi.class);
    }

    public static TopSongApi getPlayerTopSongs(){

        return getRetrofit( "https://new.scoresaber.com/").create(TopSongApi.class);
    }

    public static RecentSongApi getPlayerRecentSongs(){

        return getRetrofit( "https://new.scoresaber.com/").create(RecentSongApi.class);
    }
    public static AllmapsBeatsaverApi getallmapsSongs(){
        return getRetrofit("https://beatsaver.com/").create(AllmapsBeatsaverApi.class);
    }
}
