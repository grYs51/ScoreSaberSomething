package com.example.mobiledevproject.Beatsaver.BeatSaverMapInfo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mobiledevproject.Models.Beatsaver.beatsavermap.BeatsaverMap;
import com.example.mobiledevproject.R;

import org.ocpsoft.prettytime.PrettyTime;

import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.util.Date;

import static android.content.ContentValues.TAG;

public class InfoMapFragment extends Fragment {

    ImageView songImage;
    TextView title, mapper;
    PrettyTime p;
    Instant i;
    private BeatsaverMap beatsaverMap;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public  InfoMapFragment ( BeatsaverMap beatsaverMap){
        this.beatsaverMap = beatsaverMap;
        p = new PrettyTime();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.info_pager_fragment_info_map, container, false);

        String dt = beatsaverMap.getUploaded();

        Log.d(TAG, "onCreateView: Infragment Data: " + beatsaverMap.getName());



        title = view.findViewById(R.id.infoSongName);
        mapper = view.findViewById(R.id.infoMapper);
        songImage = view.findViewById(R.id.infoImage);

        try {
            i = Instant.parse(dt);
            mapper.setText(beatsaverMap.getMetaData().getLevelAuthorName() + " - " + p.format(Date.from(i)));
        } catch ( DateTimeParseException dtpe){
            Log.d(TAG, "catch: "+ dtpe);
            mapper.setText(beatsaverMap.getMetaData().getLevelAuthorName());
        }

        title.setText(beatsaverMap.getName());
        mapper.setText(beatsaverMap.getMetaData().getLevelAuthorName()+ " - ");

        Glide.with(getContext())
                .load("https://beatsaver.com"+ beatsaverMap.getCoverURL())
                .placeholder(R.drawable.about)
                .error(R.drawable.leaderbord)
                .into(songImage);



        return view;
    }
}