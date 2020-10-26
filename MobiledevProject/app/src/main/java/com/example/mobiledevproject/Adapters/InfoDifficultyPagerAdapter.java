package com.example.mobiledevproject.Adapters;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.mobiledevproject.Beatsaver.BeatSaverMapInfo.DifficultyInfoPar;
import com.example.mobiledevproject.Models.Beatsaver.beatsavermap.Characteristics;
import com.example.mobiledevproject.Models.Beatsaver.beatsavermap.Difficulties;
import com.example.mobiledevproject.Models.Beatsaver.beatsavermap.DifficultiesSpecs;
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



        for (Characteristics characteristicsItem : characteristics) {
            if (characteristicsItem.getName().toLowerCase().equals("standard")) {
                switch (difficulties.get(position)) {
                    case "Easy":
                        return new DifficultyInfoPar(characteristicsItem.getDifficulties().getEasy(), R.color.easy, duration, difficulties.get(position));
                    case "Normal":
                        return new DifficultyInfoPar(characteristicsItem.getDifficulties().getNormal(), R.color.mediums, duration, difficulties.get(position));
                    case "Hard":
                        return new DifficultyInfoPar(characteristicsItem.getDifficulties().getHard(), R.color.hard, duration, difficulties.get(position));
                    case "Expert":
                        return new DifficultyInfoPar(characteristicsItem.getDifficulties().getExpert(), R.color.expert, duration, difficulties.get(position));
                    case "ExpertPlus":
                        return new DifficultyInfoPar(characteristicsItem.getDifficulties().getExpertPlus(), R.color.expertPlus, duration, difficulties.get(position));
                }
            }

        }
        return new DifficultyInfoPar(null, R.color.easy, duration, difficulties.get(position)); 
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
