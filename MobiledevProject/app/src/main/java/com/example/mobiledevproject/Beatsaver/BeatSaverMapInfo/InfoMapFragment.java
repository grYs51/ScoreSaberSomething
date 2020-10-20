package com.example.mobiledevproject.Beatsaver.BeatSaverMapInfo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mobiledevproject.Adapters.InfoDifficultyPagerAdapter;
import com.example.mobiledevproject.Models.Beatsaver.beatsavermap.BeatsaverMap;
import com.example.mobiledevproject.Models.Beatsaver.beatsavermap.Characteristics;
import com.example.mobiledevproject.R;
import com.google.android.material.tabs.TabLayout;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import org.ocpsoft.prettytime.PrettyTime;

import java.lang.reflect.Array;
import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.content.ContentValues.TAG;

public class InfoMapFragment extends Fragment {

    ImageView songImage;
    TextView title, mapper, songAuthor;
    TextView songDuration, songBpm, songDownloads, songRating;
    PrettyTime p;
    Instant i;

//    MenuItem easy, normal, hard, expert, expertPlus;
    private BeatsaverMap beatsaverMap;

    private List<String> difficulties = new ArrayList<>();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    public  InfoMapFragment ( BeatsaverMap beatsaverMap){
        this.beatsaverMap = beatsaverMap;
        p = new PrettyTime();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.navigation_difficulties, menu);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.info_pager_fragment_info_map, container, false);

        String dt = beatsaverMap.getUploaded();
        findViews(view);
        setTekst(dt);

        //fill adapter
       Filldifficulties();
        ViewPager viewPager = view.findViewById(R.id.infoDifficultyPager);
        PagerAdapter pA = new InfoDifficultyPagerAdapter(getParentFragmentManager(), beatsaverMap.getMetaData().getCharacteristics(), difficulties, beatsaverMap.getMetaData().getDuration());
        viewPager.setAdapter(pA);

        TabLayout tabLayout = view.findViewById(R.id.infoDifficultyTabLayout);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

    private void setTekst(String dt) {
        try {
            i = Instant.parse(dt);
            Log.d(TAG, "onCreateView: "+ p.format(Date.from(i)));
            mapper.setText(beatsaverMap.getMetaData().getLevelAuthorName() + " - " + p.format(Date.from(i)));
        } catch ( DateTimeParseException dtpe){
            Log.d(TAG, "catch: "+ dtpe);
            mapper.setText(beatsaverMap.getMetaData().getLevelAuthorName());
        }
        //settekst
        songAuthor.setText(beatsaverMap.getMetaData().getSongAuthorName());
        title.setText(beatsaverMap.getName());
        songDuration.setText( getDurationString(beatsaverMap.getMetaData().getDuration()) );
        songBpm.setText(beatsaverMap.getMetaData().getBpm()+ "");
        songDownloads.setText(beatsaverMap.getStats().getDownloads()+"");
        songRating.setText(beatsaverMap.getStats().getUpVotes()+ " up ( "+ beatsaverMap.getStats().getDownVotes()+ " down )");

        Glide.with(getContext())
                .load("https://beatsaver.com"+ beatsaverMap.getCoverURL())
                .placeholder(R.drawable.about)
                .error(R.drawable.leaderbord)
                .into(songImage);
    }

    private void findViews(View view) {
        //info
        title = view.findViewById(R.id.infoSongName);
        mapper = view.findViewById(R.id.infoMapper);
        songAuthor = view.findViewById(R.id.infosongAuthorName);
        songImage = view.findViewById(R.id.infoImage);

        //stats
        songDuration = view.findViewById(R.id.infoSongDuration);
        songBpm = view.findViewById(R.id.infoSongBpm);
        songDownloads = view.findViewById(R.id.infoSongDownloads);
        songRating = view.findViewById(R.id.infoSongRating);
    }

    private String getDurationString(int seconds) {

        int minutes = (seconds % 3600) / 60;
        seconds = seconds % 60;

        return  twoDigitString(minutes) + " : " + twoDigitString(seconds);
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

    private void Filldifficulties() {
        int count = 0;

        if(beatsaverMap.getMetaData().getDifficulties().isEasy()){
            difficulties.add("Easy");
        }
        if(beatsaverMap.getMetaData().getDifficulties().isNormal()){
            difficulties.add("Normal");
        }
        if(beatsaverMap.getMetaData().getDifficulties().isHard()){
            difficulties.add("Hard");
        }
        if(beatsaverMap.getMetaData().getDifficulties().isExpert()){
            difficulties.add("Expert");
        }
        if(beatsaverMap.getMetaData().getDifficulties().isExpertPlus()){
            difficulties.add("ExpertPlus");
        }
        Log.d(TAG, "Filldifficulties: "+ difficulties.size());
    }
}