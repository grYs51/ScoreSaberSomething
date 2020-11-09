package com.example.mobiledevproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;


import com.example.mobiledevproject.Models.PlayerProfile.Player;
import com.example.mobiledevproject.profile.ProfileFragment;
import com.google.gson.Gson;

public class ProfileNotOwner extends AppCompatActivity {

    private static final String TAG = "ProfileNotOwnerActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_not_owner);
        Gson gson = new Gson();
        String Id =  getIntent().getStringExtra("playerNotOwner");


        Log.d(TAG, "onCreate: "+ Id);


        getSupportFragmentManager().beginTransaction()
                .add(android.R.id.content, new ProfileFragment(Id)).commit();


    }
}