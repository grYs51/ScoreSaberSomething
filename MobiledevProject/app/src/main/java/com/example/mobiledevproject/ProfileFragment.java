package com.example.mobiledevproject;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mobiledevproject.model.PlayerProfile.Player;
import com.example.mobiledevproject.model.PlayerProfile.PlayerPlayerInfo;
import com.example.mobiledevproject.model.PlayerProfile.PlayerScoreStats;
import com.example.mobiledevproject.model.PlayerProfile.ScoresSaberApi;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.stream.Stream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ProfileFragment extends Fragment implements DialogScoresaberFragment.OnInputSelected {

    private static final String TAG = "ProfileFragment";


    private  Player player_response;

    private TextView profile_Username, profile_Rank_Global, profile_Rank_Local, profile_pp, profile_Average_Rank_Acc;
    private ImageView profile_User_Image, profile_User_Country_Flag;

    private Retrofit retrofit;
    private  ScoresSaberApi scoresSaberApi;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);


       profile_Username = view.findViewById(R.id.profile_Username);
        profile_Rank_Global = view.findViewById(R.id.profile_User_Rank_Global);
        profile_Rank_Local = view.findViewById(R.id.profile_User_Rank_Local);
        profile_pp = view.findViewById(R.id.profile_user_PP);
//        profile_Average_Rank_Acc = view.findViewById(R.id.profileAverageRankedAcc);
//
        profile_User_Image = view.findViewById(R.id.imageProfile);
        profile_User_Country_Flag = view.findViewById(R.id.profile_Local_Flag);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://new.scoresaber.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        scoresSaberApi = retrofit.create(ScoresSaberApi.class);

        getUserData("76561198075540765");

//        Log.d(TAG, "onClick: Opening Dialog");
//                DialogScoresaberFragment dialog = new DialogScoresaberFragment();
//                dialog.setTargetFragment(ProfileFragment.this, 1);
//                dialog.show(getParentFragmentManager(), "DialogScoresaberFragment");



        return view;
    }

    @Override
    public void sendInput(String input) {

         Log.d(TAG, "sendInput: found incoming input: " + input);
         getUserData(input);

    }

    public  void  getUserData(String input){


        scoresSaberApi.getUserInfo(input).enqueue(new Callback<Player>() {
            @Override
            public void onResponse(Call<Player> call, Response<Player> response) {

                if ( !response.isSuccessful()){
                    Log.d(TAG, "onResponse: isSuccessful: "+response.code());
                    return;
                }

                player_response = response.body();
                PlayerScoreStats playerScoreStats = player_response.getScore_stats();
                PlayerPlayerInfo playerPlayerInfo = player_response.getPlayer_info();

               profile_Username.setText(playerPlayerInfo.getPlayer_Name());
                profile_Rank_Global.setText("#"+ playerPlayerInfo.getRank()+ " - ");
                profile_Rank_Local.setText("#"+ playerPlayerInfo.getCountry_Rank());
                profile_pp.setText(Double.toString( playerPlayerInfo.getPp())+"pp");
//                profile_Average_Rank_Acc.setText( Double.toString( Math.round(playerScoreStats.getAverage_ranked_Accuracy())) );

                Picasso.get()
                        .load("https://new.scoresaber.com"+ playerPlayerInfo.getAvatar() )
                        .placeholder(R.drawable.profile)
                        .error(R.drawable.leaderbord)
                        .into(profile_User_Image);

                Picasso.get()
                        .load("https://new.scoresaber.com/api/static/flags/"+ playerPlayerInfo.getCountry() + ".png")
                        .placeholder(R.drawable.profile)
                        .error(R.drawable.leaderbord)
                        .into(profile_User_Country_Flag);

                String historyString = playerPlayerInfo.getHistory();
                String [] stringTokens = historyString.split(",");

                

                Log.d(TAG, "onResponse: Done");
            }

            @Override
            public void onFailure(Call<Player> call, Throwable t) {
                Log.d(TAG, "onFailure: "+t.getMessage());
            }
        });
    }

}