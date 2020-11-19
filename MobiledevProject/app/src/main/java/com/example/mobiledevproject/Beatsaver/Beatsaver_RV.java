package com.example.mobiledevproject.Beatsaver;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobiledevproject.Adapters.RV.BeatsaverMapAdapter;
import com.example.mobiledevproject.ApiCall.ApiClient;
import com.example.mobiledevproject.Models.Beatsaver.MapsBeatsaver;
import com.example.mobiledevproject.Models.Beatsaver.beatsavermap.BeatsaverMap;
import com.example.mobiledevproject.R;

import java.io.Serializable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;


public class Beatsaver_RV extends Fragment implements Serializable, FilterDialog.ReturnSorting {

    private RecyclerView mapsbeatsaverRV;
    private BeatsaverMapAdapter beatsaverMapAdapter;
    private BeatsaverMapAdapter.RVClickListener listener;
    Call<MapsBeatsaver> mapList;
    Context context;
    ImageView imageView;
    ProgressBar progressBar;
    private int page_number = 0;
    //vars
    private boolean isLoading = true;
    private int pastVisibleItems, visibleItemCount, totalItemCount, previous_total = 0;
    private int view_threshold = 10;

    private String sorting = "hot";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beatsaverMapAdapter = new BeatsaverMapAdapter();
        context = getContext();

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_beatsaver_maps_rv, container, false);

        recyclerView(view);

        getProgressBar(view);

        getMaps(sorting, page_number);

        showFilter();


        addScrollListener();

        setHasOptionsMenu(true);

        return view;
    }

    private void getProgressBar(View view) {
        progressBar = view.findViewById(R.id.progress_loader);
        progressBar.setVisibility(View.VISIBLE);
    }

    private void showFilter() {
        imageView = getActivity().findViewById(R.id.addPerson);
        imageView.setImageResource(R.drawable.ic_filter_list_24);
        imageView.setVisibility(View.VISIBLE);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Toast.makeText(getContext(), "Show filter", Toast.LENGTH_SHORT).show();
                FilterDialog dialog = new FilterDialog(sorting);
                dialog.setTargetFragment(Beatsaver_RV.this, 1);
                dialog.show(getParentFragmentManager(), "FilterDialog");


            }
        });
    }

    private void addScrollListener() {
        mapsbeatsaverRV.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
        getMaps(sorting, page_number);
    }

    private void getMaps(final String sorting, final int page) {
        Log.d(TAG, "getMaps: page: " + page);
        mapList = ApiClient.getallmapsSongs().getMaps(sorting, Integer.toString(page));
        mapList.enqueue(new Callback<MapsBeatsaver>() {
            @Override
            public void onResponse(Call<MapsBeatsaver> call, Response<MapsBeatsaver> response) {
                if (!response.isSuccessful()) {
                    Log.d(TAG, "isSucces: " + response.code());
                    return;
                }
                progressBar.setVisibility(View.GONE);
                MapsBeatsaver mapsExtra = response.body();
                if (beatsaverMapAdapter.getItemCount() == 0) {
                    Log.d(TAG, "onResponse: setData" + mapsExtra.getBeatsaverMaps().toString());
                    beatsaverMapAdapter.setData(mapsExtra);
                } else {
                    beatsaverMapAdapter.addData(mapsExtra);
                    Log.d(TAG, "onResponse: AddData page: " + page + " | sorting: " + sorting);
                }
                beatsaverMapAdapter.notifyItemRangeInserted(beatsaverMapAdapter.getItemCount() - mapsExtra.getBeatsaverMaps().size(), mapsExtra.getBeatsaverMaps().size());
            }

            @Override
            public void onFailure(Call<MapsBeatsaver> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.toString());
                Toast.makeText(getContext(), "Request timed out, retrying!", Toast.LENGTH_SHORT);
                getMaps(sorting, page);
            }
        });
    }

    private void recyclerView(View view) {
        SetOnClickListener();
        mapsbeatsaverRV = view.findViewById(R.id.recycler_view_profile_maps_beatsaver);
        beatsaverMapAdapter.setlistener(listener);
        mapsbeatsaverRV.setLayoutManager(new LinearLayoutManager(context));
        mapsbeatsaverRV.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        mapsbeatsaverRV.setAdapter(beatsaverMapAdapter);
    }

    private void SetOnClickListener() {

        listener = new BeatsaverMapAdapter.RVClickListener() {
            @Override
            public void onClick(BeatsaverMap beatsaverMap) {

                Log.d(TAG, "onClick: setonclicklistenet: " + beatsaverMap.toString());
                Intent intent = new Intent(getContext(), BeatsaverMapInfo.class);
                intent.putExtra("ree", beatsaverMap);
                startActivity(intent);

            }
        };
    }

    @Override
    public void sorting(String input) {
        Log.d(TAG, "sorting: "+input);
        if(sorting != input){
            sorting = input;
            page_number = 0;
            beatsaverMapAdapter.deleteData();
            progressBar.setVisibility(View.VISIBLE);
            pastVisibleItems = 0; visibleItemCount = 0; totalItemCount = 0; previous_total = 0;
            getMaps(sorting, page_number);

        }
    }
}