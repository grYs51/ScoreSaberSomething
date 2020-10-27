package com.example.mobiledevproject.profile.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mobiledevproject.ApiCall.ApiClient;
import com.example.mobiledevproject.MainActivity;
import com.example.mobiledevproject.profile.DialogScoresaberFragment;
import com.example.mobiledevproject.R;
import com.example.mobiledevproject.Models.PlayerProfile.Player;
import com.example.mobiledevproject.Models.PlayerProfile.PlayerPlayerInfo;
import com.example.mobiledevproject.Models.PlayerProfile.PlayerScoreStats;
import com.google.android.material.navigation.NavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class profile_User_Profile extends Fragment  {

    private static final String TAG = "Profile_User_Profile";
    String playerId;
    private Player player_response;
    private TextView profile_Username, profile_Rank_Global, profile_Rank_Local, profile_pp, profile_Average_Rank_Acc, profile_Diff;
    private ImageView profile_User_Image, profile_User_Country_Flag;


    //header
    TextView headerName, headerRank;
    ImageView headerImage;


    public profile_User_Profile(String input) {

        this.playerId = input;

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile_user_profile, container, false);

        findViews(view);

        getUserData(playerId);

        return view;
    }

    private void findViews(View view) {
        profile_Username = view.findViewById(R.id.profile_Username);
        profile_Rank_Global = view.findViewById(R.id.profile_User_Rank_Global);
        profile_Rank_Local = view.findViewById(R.id.profile_User_Rank_Local);
        profile_pp = view.findViewById(R.id.profile_user_PP);
        profile_Diff = view.findViewById(R.id.profile_history_amount);
//        profile_Average_Rank_Acc = view.findViewById(R.id.profileAverageRankedAcc);
        profile_User_Image = view.findViewById(R.id.imageProfile);
        profile_User_Country_Flag = view.findViewById(R.id.profile_Local_Flag);


        //header?
        NavigationView navigationView = (NavigationView) getActivity().findViewById(R.id.navigationView);
        View header = navigationView.getHeaderView(0);


         headerRank =  header.findViewById(R.id.header_rank);
         headerName =   header.findViewById(R.id.header_User);
         headerImage=  header.findViewById(R.id.imageProfile);

    }

    public void getUserData(String input) {

        Call<Player> playerCall = ApiClient.getPlayerService().getUserInfo(input);

        playerCall.enqueue(new Callback<Player>() {
            @Override
            public void onResponse(Call<Player> call, Response<Player> response) {

                if (!response.isSuccessful()) {
                    Log.d(TAG, "onResponse: isSuccessful: " + response.code());
                    return;
                }

                player_response = response.body();
                PlayerScoreStats playerScoreStats = player_response.getScore_stats();
                PlayerPlayerInfo playerPlayerInfo = player_response.getPlayer_info();

                setText(playerPlayerInfo);





                Log.d(TAG, "onResponse: Flag: " + "https://new.scoresaber.com/api/static/flags/" + playerPlayerInfo.getCountry().toLowerCase() + ".png");
                getHistory(playerPlayerInfo);
            }

            @Override
            public void onFailure(Call<Player> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    private void setText(PlayerPlayerInfo playerPlayerInfo) {
        profile_Username.setText(playerPlayerInfo.getPlayer_Name());
        profile_Rank_Global.setText("#" + playerPlayerInfo.getRank() + " - ");
        profile_Rank_Local.setText("#" + playerPlayerInfo.getCountry_Rank());
        profile_pp.setText(Double.toString(playerPlayerInfo.getPp()) + "pp");
//                profile_Average_Rank_Acc.setText( Double.toString( Math.round(playerScoreStats.getAverage_ranked_Accuracy())) );

        // TODO: Update placeholder

        Glide.with(getContext())
                .load("https://new.scoresaber.com" + playerPlayerInfo.getAvatar().toLowerCase())
                .centerCrop()
                .placeholder(R.drawable.leaderbord)
                .into(profile_User_Image);
        // TODO: Update placeholder

        Glide.with(getContext())
                .load("https://new.scoresaber.com/api/static/flags/" + playerPlayerInfo.getCountry().toLowerCase() + ".png")
                .placeholder(R.drawable.profile)
                .error(R.drawable.leaderbord)
                .into(profile_User_Country_Flag);

        //header?

        headerRank.setText("Rank: "+playerPlayerInfo.getRank());
        headerName.setText(playerPlayerInfo.getPlayer_Name());
        // TODO: Update placeholder

        Glide.with(getContext())
                .load("https://new.scoresaber.com" + playerPlayerInfo.getAvatar().toLowerCase())
                .centerCrop()
                .placeholder(R.drawable.leaderbord)
                .into(headerImage);

    }

    private void getHistory(PlayerPlayerInfo playerPlayerInfo) {
        String historyString = playerPlayerInfo.getHistory();
        String[] stringTokens = historyString.split(",");
        int size = stringTokens.length;
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = Integer.parseInt(stringTokens[i]);
        }

        int historyDiff = arr[size - 6] - playerPlayerInfo.getRank();
        profile_Diff.setText(historyDiff + "");


        if (historyDiff < 0){
            profile_Diff.setTextColor(ContextCompat.getColor(getContext(), R.color.leaderboardDown));
        } else if(historyDiff > 0){
            profile_Diff.setTextColor(ContextCompat.getColor(getContext(), R.color.leaderboardUp));

        } else {
            profile_Diff.setTextColor(ContextCompat.getColor(getContext(), R.color.greyText));
        }
        Log.d(TAG, "onResponse: Done");
    }
}