package com.example.mobiledevproject.profile.Fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mobiledevproject.ApiCall.ApiClient;
import com.example.mobiledevproject.R;
import com.example.mobiledevproject.Models.PlayerProfile.Player;
import com.example.mobiledevproject.Models.PlayerProfile.PlayerPlayerInfo;
import com.example.mobiledevproject.Models.PlayerProfile.PlayerScoreStats;
import com.google.android.material.navigation.NavigationView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class profile_User_Profile extends Fragment {

    private static final String TAG = "Profile_User_Profile";
    private static DecimalFormat df2 = new DecimalFormat("#.##");
    String playerId;
    private Player player_response;
    private TextView profile_Username, profile_Rank_Global, profile_Rank_Local, profile_pp, profile_Average_Rank_Acc, profile_Diff;
    private ImageView profile_User_Image, profile_User_Country_Flag;
    private CardView cardShare;
    private SwipeRefreshLayout pullToRefresh;

    String playerName, Date;


    //header
    TextView headerName, headerRank;
    ImageView headerImage;
    Boolean isOwner;

    public profile_User_Profile(String input, Boolean isOwner) {

        this.playerId = input;
        this.isOwner = isOwner;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_profile_user_profile, container, false);

        findViews(view);

        setpullRefresh(view);
        pullToRefresh.setRefreshing(true);
        cardShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getContext(), "Still working on!", Toast.LENGTH_SHORT).show();
                Bitmap bitmap = getBitmapFromView(view);
                Uri uri = saveImage(bitmap);
                shareImageUri(uri);
            }
        });

        getUserData(playerId);

        return view;
    }

    private void setpullRefresh(View view) {
        pullToRefresh = view.findViewById(R.id.swipeRefresh);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.d(TAG, "onRefresh: Pulled Refresh");
                getUserData(playerId);
            }
        });
    }

    private void findViews(View view) {
        profile_Username = view.findViewById(R.id.profile_Username);
        profile_Rank_Global = view.findViewById(R.id.profile_User_Rank_Global);
        profile_Rank_Local = view.findViewById(R.id.profile_User_Rank_Local);
        profile_pp = view.findViewById(R.id.profile_user_PP);
        profile_Diff = view.findViewById(R.id.profile_history_amount);
        profile_Average_Rank_Acc = view.findViewById(R.id.profile_Acc);
        profile_User_Image = view.findViewById(R.id.imageProfile);
        profile_User_Country_Flag = view.findViewById(R.id.profile_Local_Flag);
        cardShare = view.findViewById(R.id.shareButton);


        //header?
        if (isOwner) {
            NavigationView navigationView = (NavigationView) getActivity().findViewById(R.id.navigationView);
            View header = navigationView.getHeaderView(0);
            headerRank = header.findViewById(R.id.header_rank);
            headerName = header.findViewById(R.id.header_User);
            headerImage = header.findViewById(R.id.imageProfile);
        }

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

                setText(playerPlayerInfo, playerScoreStats);
                playerName = playerPlayerInfo.getPlayer_Name();

                Log.d(TAG, "onResponse: Flag: " + "https://new.scoresaber.com/api/static/flags/" + playerPlayerInfo.getCountry().toLowerCase() + ".png");
                getHistory(playerPlayerInfo);
                pullToRefresh.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<Player> call, Throwable t) {
                Toast.makeText(getContext(), "Failed to get data, try again later", Toast.LENGTH_SHORT);
                Log.d(TAG, "onFailure: " + t.getMessage());
                pullToRefresh.setRefreshing(false);
            }
        });
    }

    private void setText(PlayerPlayerInfo playerPlayerInfo, PlayerScoreStats playerScoreStats) {
        profile_Username.setText(playerPlayerInfo.getPlayer_Name());
        profile_Rank_Global.setText("#" + playerPlayerInfo.getRank());
        profile_Rank_Local.setText(" #" + playerPlayerInfo.getCountry_Rank());
        profile_pp.setText(Double.toString(playerPlayerInfo.getPp()));
        profile_Average_Rank_Acc.setText(df2.format(playerScoreStats.getAverage_ranked_Accuracy()) + "%");

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
        if (isOwner) {
            headerRank.setText("Rank: " + playerPlayerInfo.getRank());
            headerName.setText(playerPlayerInfo.getPlayer_Name());

            // TODO: Update placeholder

            Glide.with(getContext())
                    .load("https://new.scoresaber.com" + playerPlayerInfo.getAvatar().toLowerCase())
                    .centerCrop()
                    .placeholder(R.drawable.leaderbord)
                    .into(headerImage);
        }

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


        if (historyDiff < 0) {
            profile_Diff.setTextColor(ContextCompat.getColor(getContext(), R.color.leaderboardDown));
        } else if (historyDiff > 0) {
            profile_Diff.setTextColor(ContextCompat.getColor(getContext(), R.color.leaderboardUp));

        } else {
            profile_Diff.setTextColor(ContextCompat.getColor(getContext(), R.color.greyText));
        }
        Log.d(TAG, "onResponse: Done");
    }

    public static Bitmap getBitmapFromView(View view) {
        //Define a bitmap with the same size as the view
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        //Bind a canvas to it
        Canvas canvas = new Canvas(returnedBitmap);
        //Get the view's background
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null)
            //has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas);
        else
            //does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.WHITE);
        // draw the view on the canvas
        view.draw(canvas);
        //return the bitmap
        return returnedBitmap;
    }

    private Uri saveImage(Bitmap image) {
        //TODO - Should be processed in another thread
        File imagesFolder = new File(getActivity().getCacheDir(), "images");
        ZonedDateTime dateTime = ZonedDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH:mm:ss");

        Uri uri = null;
        try {
            imagesFolder.mkdirs();
            File file = new File(imagesFolder, playerName + "_profile_" + dateTime.format(formatter) + ".png");

            FileOutputStream stream = new FileOutputStream(file);
            image.compress(Bitmap.CompressFormat.PNG, 90, stream);
            stream.flush();
            stream.close();
            uri = FileProvider.getUriForFile(getContext(), "com.example.mobiledevproject.fileprovider", file);

        } catch (IOException e) {
            Log.d(TAG, "IOException while trying to write file for sharing: " + e.getMessage());
        }
        return uri;
    }

    private void shareImageUri(Uri uri) {
        Intent intent = new Intent(android.content.Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.setType("image/png");
        startActivity(intent);
    }
}