package com.example.mobiledevproject.Adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.mobiledevproject.profile.Fragments.profile_Recent_songs;
import com.example.mobiledevproject.profile.Fragments.profile_Top_Songs;
import com.example.mobiledevproject.profile.Fragments.profile_User_Profile;


public class ProfilePagerAdapter extends FragmentPagerAdapter {

    private String tabTitles[] = new String[]{"Profile", "Top Songs", "Recent Songs"};
    String input;
    Boolean isOwner;

    public ProfilePagerAdapter(@NonNull FragmentManager fm, String input, boolean isOwner) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.input = input;
        this.isOwner = isOwner;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new profile_User_Profile(input, isOwner);
            case 1:
                return new profile_Top_Songs(input);
            case 2:
                return new profile_Recent_songs(input);
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
