package com.example.mobiledevproject.Beatsaver.BeatSaverMapInfo;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.mobiledevproject.Models.Beatsaver.beatsavermap.BeatsaverMap;
import com.example.mobiledevproject.R;
import com.example.mobiledevproject.WebViewMap;

import org.apache.commons.text.StringEscapeUtils;

import static android.content.ContentValues.TAG;

public class DescriptionMapFragment extends Fragment {
    BeatsaverMap beatsaverMap;

    Button button;
    TextView description;
    public DescriptionMapFragment(BeatsaverMap beatsaverMap){
        this.beatsaverMap = beatsaverMap;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.info_pager_fragment_description_map, container, false);

        button = view.findViewById(R.id.mapButton);
        description = view.findViewById(R.id.description);

        if( beatsaverMap.getDescription() == ""){
            description.setText("No Description given!");
        } else{
            description.setText(StringEscapeUtils.escapeHtml4(beatsaverMap.getDescription()));
        }


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://skystudioapps.com/bs-viewer/?id="+key)));
                String url = "https://skystudioapps.com/bs-viewer/?id="+beatsaverMap.getKey();
                Log.d(TAG, "onClick: " + url);
                Intent intent = new Intent(getContext(), WebViewMap.class);
                intent.putExtra("page", url);
                startActivity(intent);
            }
        });



        return view;
    }
}