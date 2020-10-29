package com.example.mobiledevproject.profile;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mobiledevproject.Adapters.ProfilePagerAdapter;
import com.example.mobiledevproject.R;
import com.google.android.material.tabs.TabLayout;


public class ProfileFragment extends Fragment implements DialogScoresaberFragment.OnInputSelected {

    private static final String TAG = "ProfileFragment";

    ViewPager viewPager;
    PagerAdapter pagerAdapter;
    TabLayout tabLayout;
    String input;
    String idImported;

    public ProfileFragment(){
        this.idImported = null;
    }

    public ProfileFragment(String idImported){
        this.idImported = idImported;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        SharedPreferences sharedPref = getActivity().getPreferences(getActivity().MODE_PRIVATE);
        input = sharedPref.getString("playerId", null);

        if (idImported == null){
            if (input == null) {
                Log.d(TAG, "onClick: Opening Dialog");
                DialogScoresaberFragment dialog = new DialogScoresaberFragment();
                dialog.setTargetFragment(ProfileFragment.this, 1);
                dialog.show(getParentFragmentManager(), "DialogScoresaberFragment");
            } else {
                createPager(input);
            }
        } else {
            createPager(idImported);
        }


    }

    public void sendInput(String input) {
        Log.d(TAG, "sendInput: found incoming input: " + input);
        this.input = input;

        SharedPreferences sharedPref = getActivity().getPreferences(getActivity().MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("playerId", input);
        editor.apply();

        createPager(input);


    }

    private void createPager(String input) {
        PagerAdapter pA = new ProfilePagerAdapter(getChildFragmentManager(), input);
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(pA);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile_adapter, container, false);

        viewPager = view.findViewById(R.id.infoPagerProfile);
        tabLayout = view.findViewById(R.id.tabLayoutProfile);

        return view;
    }
}