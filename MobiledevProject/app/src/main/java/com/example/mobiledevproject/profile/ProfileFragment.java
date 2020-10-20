package com.example.mobiledevproject.profile;

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
import com.example.mobiledevproject.profile.Fragments.profile_User_Profile;
import com.example.mobiledevproject.profile.Fragments.profile_Recent_songs;
import com.example.mobiledevproject.profile.Fragments.profile_Top_Songs;
import com.google.android.material.bottomnavigation.BottomNavigationView;



public class ProfileFragment extends Fragment implements DialogScoresaberFragment.OnInputSelected {

    private static final String TAG = "ProfileFragment";



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.d(TAG, "onClick: Opening Dialog");
        DialogScoresaberFragment dialog = new DialogScoresaberFragment();
        dialog.setTargetFragment(ProfileFragment.this, 1);
        dialog.show(getParentFragmentManager(), "DialogScoresaberFragment");

    }
    public void sendInput(String input) {
        Log.d(TAG, "sendInput: found incoming input: " + input);


        Log.d(TAG, "onViewCreated: Creating BottomNav");
        BottomNavigationView navigationView = (BottomNavigationView) getActivity().findViewById(R.id.bottomNav);
        navigationView.setOnNavigationItemSelectedListener(bottomNavListener);



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



        return inflater.inflate(R.layout.fragment_profile_adapter, container, false);
    }
}