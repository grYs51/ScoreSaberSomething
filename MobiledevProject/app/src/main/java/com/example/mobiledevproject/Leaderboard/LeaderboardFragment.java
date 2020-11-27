package com.example.mobiledevproject.Leaderboard;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobiledevproject.Adapters.RV.LeaderBoardPlayerAdapter;
import com.example.mobiledevproject.ApiCall.ApiClient;
import com.example.mobiledevproject.Models.Friends.FriendsSharedPref;
import com.example.mobiledevproject.Models.LeaderboardPlayer.LeaderboardPlayers;
import com.example.mobiledevproject.ProfileNotOwner;
import com.example.mobiledevproject.R;
import com.example.mobiledevproject.Shared.Friends;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;


public class LeaderboardFragment extends Fragment  {

    private RecyclerView leaderboardRV;
    LeaderBoardPlayerAdapter leaderBoardPlayerAdapter;
    Call<LeaderboardPlayers> playersCall;
    Friends friends;

    private int page_Number = 1;

    //vars
    private boolean isLoading = true;
    private int pastVisibleItems, visibleItemCount, totalItemCount, previous_total = 0;
    private int view_threshold = 10;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        leaderBoardPlayerAdapter = new LeaderBoardPlayerAdapter();
        friends = new Friends(getActivity().getPreferences(getActivity().MODE_PRIVATE));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_leaderboard, container, false);

        initRV(view);

        getLeaderboardPlayers(page_Number);

        addScrollListener();

        return view;
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int position = -1;
        try {
            position = ((LeaderBoardPlayerAdapter) leaderboardRV.getAdapter()).getPosition();
        } catch (Exception e){
            Log.d(TAG, e.getLocalizedMessage(), e);
            return super.onContextItemSelected(item);
        }
        switch (item.getItemId()){
            case R.id.showProfile:
                Log.d(TAG, "onContextItemSelected: ShowProfile");
                String id =  leaderBoardPlayerAdapter.getPlayerId(position);
                Intent intent = new Intent(getContext(), ProfileNotOwner.class);
                intent.putExtra("playerNotOwner", id);
                startActivity(intent);
                break;
            case R.id.addToFriends:
                Log.d(TAG, "onContextItemSelected: Add to Friends");

                //TODO: finish
                FriendsSharedPref friendsSharedPref = new FriendsSharedPref();



                break;
        }

        return super.onContextItemSelected(item);
    }

    private void addScrollListener() {
        leaderboardRV.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                visibleItemCount = ((LinearLayoutManager) recyclerView.getLayoutManager()).getChildCount();
                totalItemCount = ((LinearLayoutManager) recyclerView.getLayoutManager()).getItemCount();
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
        page_Number++;
        getLeaderboardPlayers(page_Number);
    }

    private void getLeaderboardPlayers(int page_number) {

        playersCall = ApiClient.getLeaderboardPlayers().getLeaderBoardPlayers(Integer.toString(page_number));

        playersCall.enqueue(new Callback<LeaderboardPlayers>() {
            @Override
            public void onResponse(Call<LeaderboardPlayers> call, Response<LeaderboardPlayers> response) {
                if (!response.isSuccessful()) {
                    Log.d(TAG, "isSucces: " + response.code());
                    return;
                }

                LeaderboardPlayers leaderboardPlayers = response.body();
                if(leaderBoardPlayerAdapter.getItemCount() == 0){
                    leaderBoardPlayerAdapter.setData(leaderboardPlayers);
                } else {
                    leaderBoardPlayerAdapter.addData(leaderboardPlayers);
                }
                leaderBoardPlayerAdapter.notifyItemRangeInserted(leaderBoardPlayerAdapter.getItemCount() - leaderboardPlayers.getPlayers().size(), leaderboardPlayers.getPlayers().size());
            }

            @Override
            public void onFailure(Call<LeaderboardPlayers> call, Throwable t) {
                Log.d(TAG, "onFailure: "+ t.toString());
            }
        });
    }

    private void initRV(View view) {
        leaderboardRV = view.findViewById(R.id.RV_Leaderboard);
        leaderboardRV.setLayoutManager(new LinearLayoutManager(getContext()));
        leaderboardRV.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        leaderboardRV.setAdapter(leaderBoardPlayerAdapter); // empty
    }
}