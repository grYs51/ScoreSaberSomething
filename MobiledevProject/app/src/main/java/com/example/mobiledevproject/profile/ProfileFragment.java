package com.example.mobiledevproject.profile;

import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.mobiledevproject.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;



public class ProfileFragment extends Fragment {

    private static final String TAG = "ProfileFragment";

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.d(TAG, "onViewCreated: Creating BottomNav");
        BottomNavigationView navigationView = (BottomNavigationView) getActivity().findViewById(R.id.bottomNav);
        navigationView.setOnNavigationItemSelectedListener(bottomNavListener);

        if (savedInstanceState == null) {
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.profileFragment,
                    new profile_User_Profile()).commit();
            Log.d(TAG, "onViewCreated: Opened userProfile");
        }

    }

    private BottomNavigationView.OnNavigationItemSelectedListener bottomNavListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            switch (item.getItemId()){
                case R.id.navProfile:
                    selectedFragment = new profile_User_Profile();
                    break;
                case R.id.navProfileTopScore:
                    selectedFragment = new profile_Top_Songs();
                    break;
                case R.id.navProfileRecentScore:
                    selectedFragment = new profile_Recent_songs();
                    break;
            }
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.profileFragment, selectedFragment).commit();
            Log.d(TAG, "onNavigationItemSelected: Switched to "+ selectedFragment.getTag());
            return true;
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        return inflater.inflate(R.layout.fragment_profile, container, false);
    }
}