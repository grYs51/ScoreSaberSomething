package com.example.mobiledevproject.Beatsaver;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.mobiledevproject.Adapters.InfoPagerAdapter;
import com.example.mobiledevproject.Models.Beatsaver.beatsavermap.BeatsaverMap;
import com.example.mobiledevproject.R;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class BeatsaverMapInfo extends AppCompatActivity {

    TextView songName;

    private static final String TAG = "MapInfoActivity";
    BeatsaverMap beatsaverMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beatsaver_map_info);

        beatsaverMap = (BeatsaverMap) getIntent().getSerializableExtra("ree");
        Log.d(TAG, "onCreate: dataget: Title: "+ beatsaverMap.getMetaData().getSongName() );

        songName = findViewById(R.id.InfoTitle);
        songName.setText(beatsaverMap.getMetaData().getSongName());

        TabItem tabInfo = findViewById(R.id.tabInfo);
        TabItem tabDifficulties = findViewById(R.id.tabDifficulties);
        TabItem tabDescription = findViewById(R.id.tabDescription);


        //setviewpager

        ViewPager viewPager = findViewById(R.id.infoPager);
        PagerAdapter pA = new InfoPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pA);
        //tablayout

        TabLayout tabLayout = findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);



    }

    @Nullable
    @Override
    public View onCreateView(@Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {


        return super.onCreateView(parent, name, context, attrs);
    }
}