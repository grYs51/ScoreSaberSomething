package be.grys.scoresabersomething.profile.Fragments;

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

import be.grys.scoresabersomething.Adapters.RV.ScoresaberMapAdapter;
import be.grys.scoresabersomething.ApiCall.ApiClient;
import be.grys.scoresabersomething.Models.Scores;
import be.grys.scoresabersomething.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;


public class profile_Top_Songs extends Fragment {

    private RecyclerView topsongRecyclerView;
    ScoresaberMapAdapter scoresaberMapAdapter;
    Call<Scores> mapList;
    private int page_number = 1;
    //vars
    private String playerId;
    private boolean isLoading = true;
    private int pastVisibleItems, visibleItemCount, totalItemCount, previous_total = 0;
    private int view_threshold = 8;
//    private SwipeRefreshLayout pullToRefresh;

    public profile_Top_Songs(String input) {

        this.playerId = input;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scoresaberMapAdapter = new ScoresaberMapAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile_top_songs, container, false);

        //TODO: get pull to refresh working in rv
//        pullToRefresh = view.findViewById(R.id.swipeRefreshTop);
//        pullToRefresh.setRefreshing(true);
//        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                Log.d(TAG, "onRefresh: Pulled Refresh");
//                page_number = 1;
//                scoresaberMapAdapter.setData(null);
//                getTopSongs(playerId, page_number);
//                addScrolllistener();
//            }
//        });

        initRecyclerView(view);

        getTopSongs(playerId, page_number);

        addScrolllistener();

        return view;
    }

    private void addScrolllistener() {
        topsongRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
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

        page_number++;

        getTopSongs(playerId, page_number);

    }

    public void getTopSongs(String userId, int page) {
        Log.d(TAG, "getTopSongs: " + userId + " - " + page);

        mapList = ApiClient.getPlayerTopSongs().getTopSongs(userId, Integer.toString(page));

        mapList.enqueue(new Callback<Scores>() {
            @Override
            public void onResponse(Call<Scores> call, Response<Scores> response) {
                if (!response.isSuccessful()) {
                    Log.d(TAG, "isSucces: " + response.code());
                    return;
                }
//                pullToRefresh.setRefreshing(false);
                Scores scoresaberMaps = response.body();
                if (scoresaberMapAdapter.getItemCount() == 0) {
                    scoresaberMapAdapter.setData(scoresaberMaps);
                } else {
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