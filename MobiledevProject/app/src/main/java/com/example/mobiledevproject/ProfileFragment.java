package com.example.mobiledevproject;

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

import com.example.mobiledevproject.model.Player;
import com.example.mobiledevproject.model.ScoresSaberApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ProfileFragment extends Fragment implements DialogScoresaberFragment.OnInputSelected {

    private static final String TAG = "ProfileFragment";

//    private Button mOpenDialog;
//    public TextView mInputDisplay;
    private  Player player;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        Log.d(TAG, "onClick: Opening Dialog");
                DialogScoresaberFragment dialog = new DialogScoresaberFragment();
                dialog.setTargetFragment(ProfileFragment.this, 1);
                dialog.show(getParentFragmentManager(), "DialogScoresaberFragment");

//        mOpenDialog = view.findViewById(R.id.opendialog);
//        mInputDisplay = view.findViewById(R.id.userid);

//        mOpenDialog.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d(TAG, "onClick: Opening Dialog");
//                DialogScoresaberFragment dialog = new DialogScoresaberFragment();
//                dialog.setTargetFragment(ProfileFragment.this, 1);
//                dialog.show(getParentFragmentManager(), "DialogScoresaberFragment");
//            }
//        });

        return view;
    }

    @Override
    public void sendInput(String input) {
         Log.d(TAG, "sendInput: found incoming input: " + input);
//         mInputDisplay.setText(input);

         Retrofit retrofit = new Retrofit.Builder()
                 .baseUrl("https://new.scoresaber.com/api/")
                 .addConverterFactory(GsonConverterFactory.create())
                 .build();

        ScoresSaberApi scoresSaberApi = retrofit.create(ScoresSaberApi.class);

        scoresSaberApi.getUserInfo(input).enqueue(new Callback<Player>() {
            @Override
            public void onResponse(Call<Player> call, Response<Player> response) {
                Log.d(TAG, "onResponse1: "+response.isSuccessful());

                if ( !response.isSuccessful()){
                    Log.d(TAG, "onResponse1: isSuccessful: "+response.code());
                    return;
                }
                player = response.body();
                Log.d(TAG, "onResponse: "+ player.getPlayer_info());
                Log.d(TAG, "onResponse: "+ player.getScore_stats());
            }

            @Override
            public void onFailure(Call<Player> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });

    }

}