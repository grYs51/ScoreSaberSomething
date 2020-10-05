package com.example.mobiledevproject.ApiCall;

import com.example.mobiledevproject.ApiCall.Interfaces.PlayerFullApi;
import com.example.mobiledevproject.ApiCall.Interfaces.RecentSongApi;
import com.example.mobiledevproject.ApiCall.Interfaces.TopSongApi;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit getRetrofit(){

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();

        return new Retrofit.Builder()
                .baseUrl("https://new.scoresaber.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    public static PlayerFullApi getPlayerService(){

        return getRetrofit().create(PlayerFullApi.class);
    }

    public static TopSongApi getPlayerTopSongs(){

        return getRetrofit().create(TopSongApi.class);
    }

    public static RecentSongApi getPlayerRecentSongs(){


        return getRetrofit().create(RecentSongApi.class);
    }
}
