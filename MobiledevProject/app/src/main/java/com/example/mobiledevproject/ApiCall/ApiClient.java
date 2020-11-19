package com.example.mobiledevproject.ApiCall;

import com.example.mobiledevproject.ApiCall.Interfaces.AllmapsBeatsaverApi;
import com.example.mobiledevproject.ApiCall.Interfaces.LeaderboardPlayersApi;
import com.example.mobiledevproject.ApiCall.Interfaces.PlayerFullApi;
import com.example.mobiledevproject.ApiCall.Interfaces.RankedMapsApi;
import com.example.mobiledevproject.ApiCall.Interfaces.RecentSongApi;
import com.example.mobiledevproject.ApiCall.Interfaces.TopSongApi;
import com.example.mobiledevproject.Models.LeaderboardPlayer.LeaderboardPlayers;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    static String scoresaberold = "https://scoresaber.com/";
    static String scoresaber = "https://new.scoresaber.com/";
    static String beatsaver = "https://beatsaver.com/";

    private static Retrofit getRetrofit(String baseUrl) {

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    public static PlayerFullApi getPlayerService() {

        return getRetrofit(scoresaber).create(PlayerFullApi.class);
    }

    public static TopSongApi getPlayerTopSongs() {

        return getRetrofit(scoresaber).create(TopSongApi.class);
    }

    public static RecentSongApi getPlayerRecentSongs() {

        return getRetrofit(scoresaber).create(RecentSongApi.class);
    }

    public static AllmapsBeatsaverApi getallmapsSongs() {
        return getRetrofit(beatsaver).create(AllmapsBeatsaverApi.class);
    }

    public static LeaderboardPlayersApi getLeaderboardPlayers() {
        return getRetrofit(scoresaber).create(LeaderboardPlayersApi.class);
    }

    public static RankedMapsApi getRankedMapsApi(){
        return getRetrofit(scoresaberold).create(RankedMapsApi.class);
    }
}
