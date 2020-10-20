package com.example.mobiledevproject.profile;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobiledevproject.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DialogScoresaberFragment extends AppCompatDialogFragment {

    private static final String TAG = "DialogScoresaberFragmen";

    public interface OnInputSelected{
        void sendInput (String input);
    }
    public OnInputSelected mOnInputSelected;

    private EditText mInput;
   private TextView mActionOk, mActionCancel;


    Pattern scoresaberPattern = Pattern.compile("(?:http(?:s)?:/)?(?:new\\.)?(?:scoresaber\\.com/u/)?(\\d{16,})(?:/.*)?");
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
                Log.d(TAG, "ScoresaberIdInput: closing dialog");
                getDialog().dismiss();
            }
        });
        mActionOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG, "ScoresaberIdInput: capturing input -> " + mInput.getText().toString());
                boolean flag = true;
                String input = mInput.getText().toString();
                Matcher scoresaber = scoresaberPattern.matcher(input);

                if(!input.equals("")){
                    while( scoresaber.find()){
                        if(scoresaber.group(1) != null){

                            Log.d(TAG, "ScoresaberIdInput: Regex match");
                            Toast.makeText(getActivity(),"Regex Matched!",Toast.LENGTH_SHORT).show();
                            mOnInputSelected.sendInput(scoresaber.group(1));
                            getDialog().dismiss();
                            flag = false;
                        }
                    }
                    if(flag){

                        Log.d(TAG, "ScoresaberIdInput: Regex no match");
                        Toast.makeText(getActivity(),"Regex doesn't match",Toast.LENGTH_SHORT).show();
                    }

                } else {

                    Log.d(TAG, "ScoresaberIdInput: No Input");
                    Toast.makeText(getActivity(),"No input found!",Toast.LENGTH_SHORT).show();
                }
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