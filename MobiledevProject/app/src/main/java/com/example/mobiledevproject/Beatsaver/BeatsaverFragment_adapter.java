package com.example.mobiledevproject.Beatsaver;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mobiledevproject.Adapters.BeatsaverMapAdapter;
import com.example.mobiledevproject.Models.Beatsaver.beatsavermap.BeatsaverMap;
import com.example.mobiledevproject.R;
import com.example.mobiledevproject.profile.Fragments.profile_User_Profile;

import static android.content.ContentValues.TAG;


public class BeatsaverFragment_adapter extends Fragment {

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (savedInstanceState == null) {
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.beatsaver_fragment,new Beatsaver_RV())
                    .commit();
            Log.d(TAG, "onViewCreated: Opened beatsaver_fragment");
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_beatsaver_adapter, container, false);
    }


}