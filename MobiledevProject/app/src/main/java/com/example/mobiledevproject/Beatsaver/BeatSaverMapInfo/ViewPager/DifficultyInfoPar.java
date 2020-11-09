package com.example.mobiledevproject.Beatsaver.BeatSaverMapInfo.ViewPager;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mobiledevproject.Beatsaver.BeatsaverMapInfo;
import com.example.mobiledevproject.Models.Beatsaver.beatsavermap.SpecificDiffSpec;
import com.example.mobiledevproject.R;
import com.example.mobiledevproject.WebViewMap;

import java.text.DecimalFormat;


public class DifficultyInfoPar extends Fragment {

    private static final String TAG = "Difficulty par";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    SpecificDiffSpec specificDiffSpec;
    LinearLayout hasNotes, noNotes;
    int color, duration;
    private static DecimalFormat df2 = new DecimalFormat("#.##");
    Button playbutton;

    TextView notes, bombs, njs;
    String key;

    public DifficultyInfoPar(SpecificDiffSpec specificDiffSpec, int duration, String key) {
        this.specificDiffSpec = specificDiffSpec;
        this.color = color;
        this.duration = duration;
        this.key = key;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_difficulty_info_par, container, false);

        notes = view.findViewById(R.id.InfoNotes);
        bombs = view.findViewById(R.id.InfoBombs);
        njs = view.findViewById(R.id.InfoNjs);
        hasNotes = view.findViewById(R.id.infoparLinear);
        noNotes = view.findViewById(R.id.infoparLinearifnonotes);
        playbutton = view.findViewById(R.id.mapButton);

        if (specificDiffSpec != null) {
            if(specificDiffSpec.getNotes() != 0){
                if (duration != 0) {
                    notes.setText(specificDiffSpec.getNotes() + " ( " + df2.format(specificDiffSpec.getNotes() / (double) duration) + " n/s )");
                } else {

                }
                bombs.setText(specificDiffSpec.getBombs() + "");
                njs.setText(df2.format(specificDiffSpec.getNjs())  + "");
            } else {
                hasNotes.setVisibility(View.GONE);
                noNotes.setVisibility(View.VISIBLE);
                playbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://skystudioapps.com/bs-viewer/?id="+key)));
                        String url = "https://skystudioapps.com/bs-viewer/?id="+key;
                        Log.d(TAG, "onClick: " + url);
                        Intent intent = new Intent(getContext(), WebViewMap.class);
                        intent.putExtra("page", url);
                        startActivity(intent);
                    }
                });
            }


        } else {
            hasNotes.setVisibility(View.GONE);
        }


        return view;
    }
}