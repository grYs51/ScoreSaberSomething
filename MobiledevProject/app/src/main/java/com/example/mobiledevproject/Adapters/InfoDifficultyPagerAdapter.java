package com.example.mobiledevproject.Adapters;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.mobiledevproject.Beatsaver.BeatSaverMapInfo.DifficultyInfoPar;
import com.example.mobiledevproject.Models.Beatsaver.beatsavermap.Characteristics;
import com.example.mobiledevproject.R;

import java.util.List;

public class InfoDifficultyPagerAdapter extends FragmentPagerAdapter {

    private static final String TAG = "DifficultyAdapter";
    private List<String> difficulties;
    List<Characteristics> characteristics;
    int duration;

    public InfoDifficultyPagerAdapter(@NonNull FragmentManager fm, List<Characteristics> characteristics, List<String> strings, int duration) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.characteristics = characteristics;
        this.difficulties = strings;
        this.duration = duration;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Log.d(TAG, "getItem: position: " + position);

        switch (difficulties.get(position)) {
            case "Easy":
                return new DifficultyInfoPar(characteristics.get(0).getDifficulties().getEasy(), R.color.easy, duration);
            case "Normal":
                return new DifficultyInfoPar(characteristics.get(0).getDifficulties().getNormal(), R.color.mediums, duration);
            case "Hard":
                return new DifficultyInfoPar(characteristics.get(0).getDifficulties().getHard(), R.color.hard, duration);
            case "Expert":
                return new DifficultyInfoPar(characteristics.get(0).getDifficulties().getExpert(), R.color.expert, duration);
            case "ExpertPlus":
                return new DifficultyInfoPar(characteristics.get(0).getDifficulties().getExpertPlus(), R.color.expertPlus, duration);
        }

        return null;
    }

    @Override
    public int getCount() {
        return difficulties.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        Log.d(TAG, "getPageTitle: " + difficulties.get(position));
        return difficulties.get(position);
    }
}
