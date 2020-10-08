package com.example.mobiledevproject;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobiledevproject.Adapters.BeatsaverMapAdapter;
import com.example.mobiledevproject.ApiCall.ApiClient;
import com.example.mobiledevproject.Models.Beatsaver.MapsBeatsaver;
import com.example.mobiledevproject.Models.Beatsaver.beatsavermap.BeatsaverMap;
import com.example.mobiledevproject.Models.ScoresaberMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;


public class MapsFragment extends Fragment {

    private RecyclerView mapsbeatsaverRV;
    private BeatsaverMapAdapter beatsaverMapAdapter;
    Call<MapsBeatsaver> mapList;

    private int page_number = 0;

    //vars
    private boolean isLoading = true;
    private int pastVisibleItems, visibleItemCount, totalItemCount, previous_total = 0;
    private int view_threshold = 10;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beatsaverMapAdapter = new BeatsaverMapAdapter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_maps, container, false);

        recyclerView(view);

        getMaps("hot", page_number);


        return view;
    }

    private void getMaps(final String sorting, final int page) {
        Log.d(TAG, "getMaps: page: "+page);
        mapList = ApiClient.getallmapsSongs().getMaps(sorting, Integer.toString(page));
        mapList.enqueue(new Callback<MapsBeatsaver>() {
            @Override
            public void onResponse(Call<MapsBeatsaver> call, Response<MapsBeatsaver> response) {
                if(!response.isSuccessful()){
                    Log.d(TAG, "isSucces: " + response.code());
                    return;
                }
                MapsBeatsaver mapsExtra = response.body();
                if(beatsaverMapAdapter.getItemCount() == 0){
                    beatsaverMapAdapter.setData(mapsExtra);
                } else {
                    beatsaverMapAdapter.addData(mapsExtra);
                    Log.d(TAG, "onResponse: page: "+ page+ " | sorting: "+ sorting);
                }
                beatsaverMapAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<MapsBeatsaver> call, Throwable t) {

            }
        });
    }

    private void recyclerView(View view) {
        mapsbeatsaverRV = view.findViewById(R.id.recycler_view_profile_maps_beatsaver);
        mapsbeatsaverRV.setLayoutManager(new LinearLayoutManager(getContext()));
        mapsbeatsaverRV.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        mapsbeatsaverRV.setAdapter(beatsaverMapAdapter);
    }
}