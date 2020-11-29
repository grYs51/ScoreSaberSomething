package be.grys.scoresabersomething;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import be.grys.scoresabersomething.Adapters.RV.RankedMapsAdapter;
import be.grys.scoresabersomething.ApiCall.ApiClient;
import be.grys.scoresabersomething.Models.RankedMaps.RankedMapsList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class RankedMapsFragment extends Fragment {

    private RecyclerView rankedMapsRV;
    private RankedMapsAdapter rankedMapsAdapter;

    private Context context;
    private int limit = 10, page = 1, cat = 1;

    Call<RankedMapsList> rankedMapsListCall;

    //vars
    private boolean isLoading = true;
    private int pastVisibleItems, visibleItemCount, totalItemCount, previous_total = 0;
    private int view_threshold = 4;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rankedMapsAdapter = new RankedMapsAdapter();
        context = getContext();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ranked_maps, container, false);

        InitRV(view);

        getRankedMaps(cat, page, limit);

        addScrollListener();


        return view;
    }

    private void addScrollListener() {
        rankedMapsRV.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
        page++;
        getRankedMaps(cat, page, limit);
    }

    private void getRankedMaps(int cat, int page, int limit) {
        Log.d(TAG, "getRankedMaps: page: " + page);

        rankedMapsListCall = ApiClient.getRankedMapsApi().getRankedMaps( "get-leaderboards", Integer.toString(cat), Integer.toString(page), Integer.toString(limit));
        rankedMapsListCall.enqueue(new Callback<RankedMapsList>() {
            @Override
            public void onResponse(Call<RankedMapsList> call, Response<RankedMapsList> response) {
                if (!response.isSuccessful()) {
                    Log.d(TAG, "isSucces: " + response.code());
                    return;
                }

                RankedMapsList rankedMapsListresponse = response.body();

                if (rankedMapsAdapter.getItemCount() == 0) {
                    rankedMapsAdapter.setData(rankedMapsListresponse);
                } else {
                    rankedMapsAdapter.addData(rankedMapsListresponse);
                }
                rankedMapsAdapter.notifyItemRangeInserted(rankedMapsAdapter.getItemCount() - rankedMapsListresponse.getRankedMaps().size(), rankedMapsListresponse.getRankedMaps().size());

            }

            @Override
            public void onFailure(Call<RankedMapsList> call, Throwable t) {

                Log.d(TAG, "onFailure: " + t.toString());
                Toast.makeText(getContext(), "Request timed out, retrying!", Toast.LENGTH_SHORT);
            }
        });
    }

    private void InitRV(View view) {
        rankedMapsRV = view.findViewById(R.id.rankedMapsRV);
        rankedMapsRV.setLayoutManager(new LinearLayoutManager(context));
        rankedMapsRV.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        rankedMapsRV.setAdapter(rankedMapsAdapter); // empty
    }
}