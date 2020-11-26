package com.example.mobiledevproject.Beatsaver;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mobiledevproject.R;

import static android.content.ContentValues.TAG;


public class FilterDialog extends DialogFragment {

    String sorting;
    Handler handler = new Handler();
    TextView hot, rating, latest, downloads, plays;

    public interface ReturnSorting {
        void sorting(String input);
    }
    ReturnSorting returnSorting;

    public FilterDialog(String sorting){
        this.sorting = sorting;
    }


    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        
        returnSorting.sorting(sorting);
        super.onDismiss(dialog);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filter, container, false);

        hot = view.findViewById(R.id.filterHot);
        rating = view.findViewById(R.id.filterRating);
        latest = view.findViewById(R.id.filterLatest);
        downloads = view.findViewById(R.id.filterDownloads);
        plays = view.findViewById(R.id.filterPlays);

        highlightselected();

        SetListener();

        return view;
    }

    private void SetListener() {
        hot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sorting = "hot";
                highlightselected();
                dismissdelay();
            }
        });
        rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sorting = "rating";
                highlightselected();
                dismissdelay();

            }
        });
        latest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sorting = "latest";
                highlightselected();
                dismissdelay();

            }
        });
        downloads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sorting = "downloads";
                highlightselected();
                dismissdelay();

            }
        });
        plays.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sorting = "plays";
                highlightselected();
                getDialog().dismiss();

            }
        });
    }

    private void dismissdelay(){
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getDialog().dismiss();

            }
        }, 100);
    }

    private void highlightselected() {

        hot.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.tablayout_selected));
        rating.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.tablayout_selected));
        latest.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.tablayout_selected));
        downloads.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.tablayout_selected));
        plays.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.tablayout_selected));

        switch (sorting.toLowerCase()){
            case "hot":
                hot.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.tablayout_unselected));
                break;
            case "rating":
                rating.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.tablayout_unselected));
                break;
            case "latest":
                latest.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.tablayout_unselected));
                break;
            case "downloads":
                downloads.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.tablayout_unselected));
                break;
            case "plays":
                plays.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.tablayout_unselected));
                break;
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            returnSorting = (ReturnSorting) getTargetFragment();
        } catch (ClassCastException e){
            Log.d(TAG, "onAttach: ClassCastExeption" + e.getMessage());
        }
    }
}