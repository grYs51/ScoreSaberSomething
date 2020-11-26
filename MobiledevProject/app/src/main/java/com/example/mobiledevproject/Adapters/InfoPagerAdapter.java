package com.example.mobiledevproject.Adapters;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.mobiledevproject.Beatsaver.BeatSaverMapInfo.DescriptionMapFragment;
import com.example.mobiledevproject.Beatsaver.BeatSaverMapInfo.InfoMapFragment;
import com.example.mobiledevproject.Models.Beatsaver.beatsavermap.BeatsaverMap;

public class InfoPagerAdapter extends FragmentPagerAdapter {

    private static final String TAG = "InfoAdapter";

    private String tabTitles[] = new String[]{"Info", "Extra"};
    private BeatsaverMap beatsaverMap;

    public InfoPagerAdapter(@NonNull FragmentManager fm, BeatsaverMap beatsaverMap) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.beatsaverMap = beatsaverMap;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new InfoMapFragment(beatsaverMap);
            case 1:
                return new DescriptionMapFragment(beatsaverMap);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}