package com.example.mobiledevproject.profile.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.mobiledevproject.Adapters.ScoresaberMapAdapter;
import com.example.mobiledevproject.ApiCall.ApiClient;
import com.example.mobiledevproject.Models.Scores;
import com.example.mobiledevproject.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;


public class profile_Top_Songs extends Fragment {

    private RecyclerView topsongRecyclerView;
    private ProgressBar progressBar;
    ScoresaberMapAdapter scoresaberMapAdapter;
    Call<Scores> mapList;

    private int page_number = 1;

    //vars
    private boolean isLoading = true;
    private int pastVisibleItems, visibleItemCount, totalItemCount, previous_total = 0;
    private int view_threshold = 8;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scoresaberMapAdapter = new ScoresaberMapAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile_top_songs, container, false);

        initRecyclerView(view);

        getTopSongs("76561198075540765", page_number);

        addScrolllistener();

        return view;
    }

    private void addScrolllistener() {
        topsongRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                visibleItemCount = ((LinearLayoutManager)recyclerView.getLayoutManager()).getChildCount();
                totalItemCount = ((LinearLayoutManager)recyclerView.getLayoutManager()).getItemCount();
                pastVisibleItems = ((LinearLayoutManager)recyclerView.getLayoutManager()).findFirstVisibleItemPosition();

                if(dy > 0){
                    if (isLoading){
                        if (totalItemCount > previous_total){
                            isLoading = false;
                            previous_total = totalItemCount;
                        }
                    } else if (!isLoading && (totalItemCount - visibleItemCount) <= (pastVisibleItems + view_threshold)){
                        performPagination();
                        isLoading = true;
                    }
                }
            }
        });
    }

    private void performPagination(){
        page_number++;
        getTopSongs("76561198075540765", page_number);
    }

    public void getTopSongs(String userId, int page){
        Log.d(TAG, "getTopSongs: "+ userId + " - " + page);

        mapList = ApiClient.getPlayerTopSongs().getTopSongs(userId, Integer.toString( page));
        
        mapList.enqueue(new Callback<Scores>() {
            @Override
            public void onResponse(Call<Scores> call, Response<Scores> response) {
                if (!response.isSuccessful()){
                    Log.d(TAG, "isSucces: "+ response.code());
                    return;
                }
                Scores scoresaberMaps = response.body();
                if (scoresaberMapAdapter.getItemCount() == 0){
                    scoresaberMapAdapter.setData(scoresaberMaps);
                } else{
                    scoresaberMapAdapter.addData(scoresaberMaps);
                }
                scoresaberMapAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Scores> call, Throwable t) {

            }
        });
        

    }

    private void initRecyclerView(View view) {
        topsongRecyclerView = view.findViewById(R.id.recycler_view_profile_topscore);
        topsongRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        topsongRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        topsongRecyclerView.setAdapter(scoresaberMapAdapter); // empty
    }
}