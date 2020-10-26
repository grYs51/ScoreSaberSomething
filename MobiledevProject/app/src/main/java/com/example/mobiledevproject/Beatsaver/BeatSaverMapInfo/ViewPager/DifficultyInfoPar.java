package com.example.mobiledevproject.Beatsaver.BeatSaverMapInfo.ViewPager;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mobiledevproject.Models.Beatsaver.beatsavermap.SpecificDiffSpec;
import com.example.mobiledevproject.R;

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

    TextView notes, bombs, njs;

    public DifficultyInfoPar(SpecificDiffSpec specificDiffSpec,
                             int duration) {
        this.specificDiffSpec = specificDiffSpec;
        this.color = color;
        this.duration = duration;
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

        if (specificDiffSpec != null) {
            if (duration != 0) {
                notes.setText(specificDiffSpec.getNotes() + " ( " + df2.format(specificDiffSpec.getNotes() / (double) duration) + " n/s )");
            } else {

            }
            bombs.setText(specificDiffSpec.getBombs() + "");
            njs.setText(specificDiffSpec.getNjs() + "");

        } else {

            hasNotes.setVisibility(View.GONE);
            noNotes.setVisibility(View.VISIBLE);
        }


        return view;
    }
}