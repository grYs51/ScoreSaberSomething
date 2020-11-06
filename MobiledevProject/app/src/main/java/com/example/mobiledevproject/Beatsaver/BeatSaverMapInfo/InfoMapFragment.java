package com.example.mobiledevproject.Beatsaver.BeatSaverMapInfo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mobiledevproject.Adapters.InfoModesAdapter;
import com.example.mobiledevproject.Adapters.RV.InfoMapAdapter;
import com.example.mobiledevproject.Models.Beatsaver.beatsavermap.BeatsaverMap;
import com.example.mobiledevproject.Models.Beatsaver.beatsavermap.Characteristics;
import com.example.mobiledevproject.R;
import com.google.android.material.tabs.TabLayout;

import org.ocpsoft.prettytime.PrettyTime;

import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.content.ContentValues.TAG;

public class InfoMapFragment extends Fragment {

//    ImageView songImage;
    TextView title, mapper, songAuthor;
    TextView songDuration, songBpm, songDownloads, songRatingUp, songRatingDown;
    PrettyTime p;
    Instant i;

    private BeatsaverMap beatsaverMap;
    
    public InfoMapFragment(BeatsaverMap beatsaverMap) {
        this.beatsaverMap = beatsaverMap;
        p = new PrettyTime();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.info_pager_fragment_info_map, container, false);

        String dt = beatsaverMap.getUploaded();
        findViews(view);
        setTekst(dt);

        List<String> myList = new ArrayList<String>();
        for (Characteristics characteristics : beatsaverMap.getMetaData().getCharacteristics()) {
            myList.add(characteristics.getName());
        }

        ViewPager viewPager = view.findViewById(R.id.infoModePager);
        InfoModesAdapter infoModesAdapter = new InfoModesAdapter(getParentFragmentManager(), beatsaverMap.getMetaData().getCharacteristics(), myList, beatsaverMap.getMetaData().getDuration(), beatsaverMap.getKey());
        viewPager.setAdapter(infoModesAdapter);

        TabLayout tabLayout = view.findViewById(R.id.tabLayoutModes);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }


    private void findViews(View view) {
        //info
        title = view.findViewById(R.id.infoSongName);
        mapper = view.findViewById(R.id.infoMapper);
        songAuthor = view.findViewById(R.id.infosongAuthorName);

        //stats
        songDuration = view.findViewById(R.id.infoSongDuration);
        songBpm = view.findViewById(R.id.infoSongBpm);
        songDownloads = view.findViewById(R.id.infoSongDownloads);
        songRatingUp = view.findViewById(R.id.infoSongRatingUp);
        songRatingDown = view.findViewById(R.id.infoSongRatingDown);
    }

    private void setTekst(String dt) {
        try {
            i = Instant.parse(dt);
            Log.d(TAG, "onCreateView: " + p.format(Date.from(i)));
            mapper.setText(beatsaverMap.getMetaData().getLevelAuthorName() + " - " + p.format(Date.from(i)));
        } catch (DateTimeParseException dtpe) {
            Log.d(TAG, "catch: " + dtpe);
            mapper.setText(beatsaverMap.getMetaData().getLevelAuthorName());
        }
        //settekst
        songAuthor.setText(beatsaverMap.getMetaData().getSongAuthorName());
        title.setText(beatsaverMap.getName());
        songDuration.setText(getDurationString(beatsaverMap.getMetaData().getDuration()));
        songBpm.setText(beatsaverMap.getMetaData().getBpm() + "");
        songDownloads.setText(beatsaverMap.getStats().getDownloads() + "");
        songRatingUp.setText(beatsaverMap.getStats().getUpVotes() + " up");
        songRatingDown.setText(beatsaverMap.getStats().getDownVotes() + " down");


//TODO: change placeholder
//        Glide.with(getContext())
//                .load("https://beatsaver.com" + beatsaverMap.getCoverURL())
//                .placeholder(R.drawable.about)
//                .error(R.drawable.leaderbord)
//                .into(songImage);
    }

    private String getDurationString(int seconds) {

        int minutes = (seconds % 3600) / 60;
        seconds = seconds % 60;

        return twoDigitString(minutes) + " : " + twoDigitString(seconds);
    }

    private String twoDigitString(int number) {

        if (number == 0) {
            return "00";
        }

        if (number / 10 == 0) {
            return "0" + number;
        }

        return String.valueOf(number);
    }

}