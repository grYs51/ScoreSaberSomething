package com.example.mobiledevproject.Adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.mobiledevproject.Beatsaver.BeatSaverMapInfo.ViewPager.ModesInfo;
import com.example.mobiledevproject.Models.Beatsaver.beatsavermap.Characteristics;

import java.util.List;

public class InfoModesAdapter extends FragmentPagerAdapter{
List<Characteristics> characteristics;
List<String> modelist;
int duration;
    public InfoModesAdapter(@NonNull FragmentManager fm, List<Characteristics> characteristics, List<String> modeList, int duration) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        this.characteristics = characteristics;
        this.modelist = modeList;
        this.duration = duration;

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        for (Characteristics item: characteristics) {
            return new ModesInfo(item.getDifficulties(), duration, item.getName() );
        }



        return null;
    }

    @Override
    public int getCount() {
        return modelist.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return characteristics.get(position).getName();
    }
}
