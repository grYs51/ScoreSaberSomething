package com.example.mobiledevproject.profile.Fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mobiledevproject.Adapters.Scoresaber.ScoresaberMapAdapter;
import com.example.mobiledevproject.ApiCall.ApiClient;
import com.example.mobiledevproject.Models.Scores;
import com.example.mobiledevproject.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;


public class profile_Recent_songs extends Fragment {

    private RecyclerView recentsongRecyclerView;
    ScoresaberMapAdapter scoresaberMapAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scoresaberMapAdapter = new ScoresaberMapAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile__recent_songs, container, false);

        recentsongRecyclerView = view.findViewById(R.id.recycler_view_profile_recentScore);
        recentsongRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recentsongRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        recentsongRecyclerView.setAdapter(scoresaberMapAdapter);

        getRecentSongs("76561198075540765", "1");
        return view;
    }
    
    public void getRecentSongs(String userId, String page){
        Log.d(TAG, "getRecentSongs: " + userId + " - " + page);

        Call<Scores> mapList = ApiClient.getPlayerRecentSongs().getRecentSong(userId, page);
        mapList.enqueue(new Callback<Scores>() {
            @Override
            public void onResponse(Call<Scores> call, Response<Scores> response) {
                if (!response.isSuccessful()){
                    Log.d(TAG, "isSucces: "+ response.code());
                    return;
                }
                Scores scoresabermaps = response.body();
                scoresaberMapAdapter.setData(scoresabermaps);
                scoresaberMapAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Scores> call, Throwable t) {

            }
        });
    }
}