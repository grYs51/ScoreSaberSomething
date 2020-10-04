package com.example.mobiledevproject.profile.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mobiledevproject.R;


public class profile_Top_Songs extends Fragment {

    private RecyclerView topsongRecyclerView;
    private RecyclerView.Adapter madapter;
    private RecyclerView.LayoutManager layoutManager;


    public profile_Top_Songs() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile__top__songs, container, false);
    }
}