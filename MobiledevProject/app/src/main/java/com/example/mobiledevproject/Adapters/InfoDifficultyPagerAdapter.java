package com.example.mobiledevproject.Adapters;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.mobiledevproject.Beatsaver.BeatSaverMapInfo.ViewPager.DifficultyInfoPar;
import com.example.mobiledevproject.Models.Beatsaver.beatsavermap.Characteristics;
import com.example.mobiledevproject.Models.Beatsaver.beatsavermap.Difficulties;
import com.example.mobiledevproject.Models.Beatsaver.beatsavermap.DifficultiesSpecs;
import com.example.mobiledevproject.Models.Beatsaver.beatsavermap.SpecificDiffSpec;
import com.example.mobiledevproject.R;

import java.util.List;
import java.util.Map;

public class InfoDifficultyPagerAdapter extends FragmentPagerAdapter {

    private static final String TAG = "DifficultyAdapter";
DifficultiesSpecs difficultiesSpecs;
    int duration;


    public InfoDifficultyPagerAdapter(@NonNull FragmentManager fm, DifficultiesSpecs difficultiesSpecs, int duration) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.difficultiesSpecs = difficultiesSpecs;
        this.duration = duration;
    }



    @NonNull
    @Override
    public Fragment getItem(int position) {
        Log.d(TAG, "getItem: position: " + position);



        return new DifficultyInfoPar(null, R.color.easy, duration);
    }


    @Override
    public int getCount() {
        return 0 ;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        Log.d(TAG, "getPageTitle: " + difficultiesSpecs);
        return "";
    }
}
