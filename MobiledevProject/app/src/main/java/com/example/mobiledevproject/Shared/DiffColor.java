package com.example.mobiledevproject.Shared;


import android.graphics.Color;

import com.example.mobiledevproject.R;

public class DiffColor {

    int diff;

    public DiffColor(int diff) {
        this.diff = diff;
    }

    public int getDiffColor(){


        switch (diff){
            case 1: //easy
                return R.color.easy;
            case 3: //medium
                return R.color.mediums;
            case 5: //hard
                return R.color.hard;
            case 7: //expert
                return R.color.expert;
            case 9: //expert+
                return R.color.expertPlus;
            default:
                return R.color.black;
        }


    }
}
