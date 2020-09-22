package com.example.mobiledevproject;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


public class DialogScoresaberFragment extends DialogFragment {

    private static final String TAG = "DialogScoresaberFragmen";

    public interface OnInputSelected{
        void sendInput (String input);
    }
    public OnInputSelected mOnInputSelected;

    private EditText mInput;
    private TextView mActionOk, mActionCancel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_scoresaber, container, false);

        mActionOk = view.findViewById(R.id.OkButton);
        mActionCancel = view.findViewById(R.id.cancelButton);
        mInput = view.findViewById(R.id.scoresaberId);

        mActionCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: closing dialog");
                getDialog().dismiss();
            }
        });
        mActionOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: capturing input");

                String input = mInput.getText().toString();
                if(!input.equals("")){

                    mOnInputSelected.sendInput(input);
                }
                getDialog().dismiss();
            }
        });

        return  view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try{
            mOnInputSelected = (OnInputSelected) getTargetFragment();
        } catch (ClassCastException e){
            Log.e(TAG, "onAttach: ClassCastExeption" + e.getMessage());
        }
    }
}