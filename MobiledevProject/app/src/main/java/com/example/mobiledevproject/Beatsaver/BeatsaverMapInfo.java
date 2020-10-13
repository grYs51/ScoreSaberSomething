package com.example.mobiledevproject.Beatsaver;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
import com.example.mobiledevproject.Models.Beatsaver.beatsavermap.BeatsaverMap;
import com.example.mobiledevproject.R;

public class BeatsaverMapInfo extends AppCompatActivity {

    ImageView mapImage;
    TextView songName;

    private static final String TAG = "MapInfoActivity";
    BeatsaverMap beatsaverMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beatsaver_map_info);

        beatsaverMap = (BeatsaverMap) getIntent().getSerializableExtra("ree");
        Log.d(TAG, "onCreate: dataget: Title: "+ beatsaverMap.getMetaData().getSongName() );

        songName = findViewById(R.id.title);
        mapImage = findViewById(R.id.toolbar_image);

        songName.setText(beatsaverMap.getName());

        Glide.with(this)
                .load("https://beatsaver.com"+ beatsaverMap.getCoverURL())
                .placeholder(R.drawable.about)
                .error(R.drawable.leaderbord)
                .into(mapImage);

    }

    @Nullable
    @Override
    public View onCreateView(@Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {


        return super.onCreateView(parent, name, context, attrs);
    }
}