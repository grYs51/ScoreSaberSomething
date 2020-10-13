package com.example.mobiledevproject.Beatsaver;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.example.mobiledevproject.Models.Beatsaver.beatsavermap.BeatsaverMap;
import com.example.mobiledevproject.R;
import static android.content.ContentValues.TAG;

public class Beatsaver_map_infoFragment extends Fragment {
BeatsaverMap beatsaverMap;
LinearLayout linearLayout;
    public Beatsaver_map_infoFragment(BeatsaverMap beatsaverMap){
        Log.d(TAG, "Beatsaver_map_info: "+beatsaverMap.toString());

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_beatsaver_info_map, container, false);

        return view;
    }

}