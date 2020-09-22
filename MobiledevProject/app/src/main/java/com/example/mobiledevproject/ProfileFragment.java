package com.example.mobiledevproject;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class ProfileFragment extends Fragment implements DialogScoresaberFragment.OnInputSelected {

    private static final String TAG = "ProfileFragment";

    private Button mOpenDialog;
    public TextView mInputDisplay;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);


        mOpenDialog = view.findViewById(R.id.opendialog);
        mInputDisplay = view.findViewById(R.id.userid);

        mOpenDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Opening Dialog");
                DialogScoresaberFragment dialog = new DialogScoresaberFragment();
                dialog.setTargetFragment(ProfileFragment.this, 1);
                dialog.show(getParentFragmentManager(), "DialogScoresaberFragment");
            }
        });

        return view;
    }

    @Override
    public void sendInput(String input) {
         Log.d(TAG, "sendInput: found incoming input: " + input);

         mInputDisplay.setText(input);
    }

    //    public ProfileFragment() {
//        // Required empty public constructor
//    }
//
//
//    // TODO: Rename and change types and number of parameters
//    public static ProfileFragment newInstance(String param1, String param2) {
//        ProfileFragment fragment = new ProfileFragment();
//        Bundle args = new Bundle();
//
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        if(true){
//            Toast.makeText(getActivity(),"No user Found!",Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_profile, container, false);
//    }

}