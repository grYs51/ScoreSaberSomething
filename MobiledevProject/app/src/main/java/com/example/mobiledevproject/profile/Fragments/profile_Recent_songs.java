package com.example.mobiledevproject.profile.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mobiledevproject.Adapters.RV.ScoresaberMapAdapter;
import com.example.mobiledevproject.ApiCall.ApiClient;
import com.example.mobiledevproject.Models.Scores;
import com.example.mobiledevproject.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;


public class profile_Recent_songs extends Fragment {

    RecyclerView recentsongRecyclerView;
    ScoresaberMapAdapter scoresaberMapAdapter;
    Call<Scores> mapList;

    private int page_number = 1;

    //vars
    String playerId;
    private boolean isLoading = true;
    private int pastVisibleItems, visibleItemCount, totalItemCount, previous_total = 0;
    private int view_threshold = 8;

    public profile_Recent_songs(String input) {
        this.playerId = input;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scoresaberMapAdapter = new ScoresaberMapAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile_recent_songs, container, false);

        recyclerView(view);

        //TODO: get pull to refresh working in rv

        getRecentSongs(playerId, page_number);

        addScrollListener();

        return view;
    }


    private void addScrollListener() {
        recentsongRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                visibleItemCount = recyclerView.getLayoutManager().getChildCount();
                totalItemCount = recyclerView.getLayoutManager().getItemCount();
                pastVisibleItems = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();

                if (dy > 0) {
                    if (isLoading) {
                        if (totalItemCount > previous_total) {
                            isLoading = false;
                            previous_total = totalItemCount;
                        }
                    } else if (!isLoading && (totalItemCount - visibleItemCount) <= (pastVisibleItems + view_threshold)) {
                        performPagination();
                        isLoading = true;
                    }
                }
            }
        });
    }

    private void performPagination() {
        page_number++;
        getRecentSongs(playerId, page_number);
    }

    public void getRecentSongs(String userId, final int page) {
        Log.d(TAG, "getRecentSongs: " + userId + " - " + page);

        // progressbar visible

        mapList = ApiClient.getPlayerRecentSongs().getRecentSong(userId, Integer.toString(page));
        mapList.enqueue(new Callback<Scores>() {
            @Override
            public void onResponse(Call<Scores> call, Response<Scores> response) {
                if (!response.isSuccessful()) {
                    Log.d(TAG, "isSucces: " + response.code());
                    return;
                }

                // progressbar gone

                Scores scoresabermaps = response.body();

                if (scoresaberMapAdapter.getItemCount() == 0) {
                    scoresaberMapAdapter.setData(scoresabermaps);
                    Log.d(TAG, "onResponse: First page is loaded");

                } else {
                    scoresaberMapAdapter.addData(scoresabermaps);
                    Log.d(TAG, "onResponse: page: " + page + " loaded");

                }

                scoresaberMapAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Scores> call, Throwable t) {

            }
        });


    }

    private void recyclerView(View view) {
        recentsongRecyclerView = view.findViewById(R.id.recycler_view_profile_recentScore);
        recentsongRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recentsongRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        recentsongRecyclerView.setAdapter(scoresaberMapAdapter);
    }

}