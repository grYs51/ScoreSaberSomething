package com.example.mobiledevproject.profile.Fragments;

import android.os.Bundle;

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


public class profile_Top_Songs extends Fragment {

    private RecyclerView topsongRecyclerView;
    ScoresaberMapAdapter scoresaberMapAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        scoresaberMapAdapter = new ScoresaberMapAdapter();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile__top__songs, container, false);

        topsongRecyclerView = view.findViewById(R.id.recycler_view_profile_topscore);
        topsongRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        topsongRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        topsongRecyclerView.setAdapter(scoresaberMapAdapter); // empty

        getTopSongs("76561198075540765", "1");
        return view;
    }



    public void getTopSongs(String userId, String page){
        Log.d(TAG, "getTopSongs: "+ userId + " - " + page);

        Call<Scores> mapList = ApiClient.getPlayerTopSongs().getTopSongs(userId,page);
        
        mapList.enqueue(new Callback<Scores>() {
            @Override
            public void onResponse(Call<Scores> call, Response<Scores> response) {
                if (!response.isSuccessful()){
                    Log.d(TAG, "isSucces: "+ response.code());
                    return;
                }
                Scores scoresaberMaps = response.body();
                scoresaberMapAdapter.setData(scoresaberMaps);
                scoresaberMapAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Scores> call, Throwable t) {

            }
        });
        

    }
}