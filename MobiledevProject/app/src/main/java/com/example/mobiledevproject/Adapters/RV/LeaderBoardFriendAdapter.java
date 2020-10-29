package com.example.mobiledevproject.Adapters.RV;

import android.content.Context;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mobiledevproject.ApiCall.ApiClient;
import com.example.mobiledevproject.Models.Friends.FriendList;
import com.example.mobiledevproject.Models.Friends.FriendsSharedPref;
import com.example.mobiledevproject.Models.Friends.Testing;
import com.example.mobiledevproject.Models.LeaderboardPlayer.LPlayer;
import com.example.mobiledevproject.Models.PlayerProfile.Player;
import com.example.mobiledevproject.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class LeaderBoardFriendAdapter extends RecyclerView.Adapter<LeaderBoardFriendAdapter.ViewHolder> {
    private Context context;

    ArrayList<Testing> testings = new ArrayList<>();

    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
    public Player getData(int position){
        return testings.get(position).getlPlayer();
    }

    public void setData(FriendList friendList) {
        if (friendList != null) {

            for (FriendsSharedPref fr : friendList.getFriends()) {
                Testing fullPlayer = new Testing();
                fullPlayer.setFriendsSharedPref(fr);
                fullPlayer.setlPlayer(null);
                testings.add(fullPlayer);
            }
        }


    }

    public void addData(FriendsSharedPref friendsSharedPref, Player lPlayer) {
        Testing fullPlayer = new Testing();
        fullPlayer.setFriendsSharedPref(friendsSharedPref);
        fullPlayer.setlPlayer(lPlayer);
        testings.add(fullPlayer);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_leaderboard_player, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.playerName.setText(testings.get(position).getFriendsSharedPref().getName());

        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                setPosition(position);
                return false;
            }
        });
        Glide.with(context)
                .load("https://new.scoresaber.com" + testings.get(position).getFriendsSharedPref().getAvatar())
                .placeholder(R.drawable.about)
                .error(R.drawable.leaderbord)
                .into(holder.avatar);


        if (testings.get(position).getlPlayer() == null) {


            Call<Player> playerCall = ApiClient.getPlayerService().getUserInfo(testings.get(position).getFriendsSharedPref().getId());
            playerCall.enqueue(new Callback<Player>() {
                LPlayer playerObject = new LPlayer();

                @Override
                public void onResponse(Call<Player> call, Response<Player> response) {
                    if (!response.isSuccessful()) {
                        Log.d(TAG, "onResponse: isSuccessful: " + response.code());
                        return;
                    }


                    Player player = response.body();

                    testings.get(position).setlPlayer(player);
                    setItem(holder, player);

                }

                @Override
                public void onFailure(Call<Player> call, Throwable t) {

                }
            });
        } else {
            setItem(holder, testings.get(position).getlPlayer());
        }

    }

    private void setItem(ViewHolder holder, Player lPlayer) {
        holder.playerName.setText(lPlayer.getPlayer_info().getPlayer_Name());
        holder.playerRank.setText("#" + lPlayer.getPlayer_info().getRank());
        holder.playerpp.setText(lPlayer.getPlayer_info().getPp() + "pp");

        // TODO: Update placeholder

//        Glide.with(context)
//                .load("https://new.scoresaber.com" + lPlayer.getAvatar())
//                .placeholder(R.drawable.about)
//                .error(R.drawable.leaderbord)
//                .into(holder.avatar);

        // TODO: Update placeholder


        Glide.with(context)
                .load("https://new.scoresaber.com/api/static/flags/" + lPlayer.getPlayer_info().getCountry().toLowerCase() + ".png")
                .placeholder(R.drawable.about)
                .error(R.drawable.leaderbord)
                .into(holder.flag);

        String historyString = lPlayer.getPlayer_info().getHistory();
        String[] stringTokens = historyString.split(",");
        int size = stringTokens.length;
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = Integer.parseInt(stringTokens[i]);
        }
        int historyDiff;
        if (arr.length < 6) {
            historyDiff = arr[size - (size - 1)] - lPlayer.getPlayer_info().getRank();
        } else {
            historyDiff = arr[size - 6] - lPlayer.getPlayer_info().getRank();
        }

        Log.d(TAG, "setItem: Difference: " + historyDiff);
        if (historyDiff > 0) {
            holder.linearLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.leaderboardUp));
        } else if (historyDiff == 0) {
            holder.linearLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.greyText));
        } else {
            holder.linearLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.leaderboardDown));
        }
    }

    @Override
    public int getItemCount() {
        return testings.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        ImageView avatar, flag;
        TextView playerName, playerpp, playerRank;
        LinearLayout linearLayout;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);


            avatar = itemView.findViewById(R.id.leaderboard_ImagePlayer);
            flag = itemView.findViewById(R.id.leaderboard_Local_Flag);

            cardView = itemView.findViewById(R.id.item_leaderboardPlayerCard);
            itemView.setOnCreateContextMenuListener(this);


            playerName = itemView.findViewById(R.id.leaderboard_PlayerName);
            playerpp = itemView.findViewById(R.id.leaderboard_Pp);
            playerRank = itemView.findViewById(R.id.leaderboard_Position);

            linearLayout = itemView.findViewById(R.id.leaderboard_Linear);

        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.add(Menu.NONE, R.id.showProfile, Menu.NONE, "Show Profile");
            menu.add(Menu.NONE, R.id.removePlayer, Menu.NONE, "Remove Player");

        }
    }
}
