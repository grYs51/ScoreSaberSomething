package be.grys.scoresabersomething.Adapters.RV;

import android.content.Context;
import android.content.SharedPreferences;
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
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import be.grys.scoresabersomething.ApiCall.ApiClient;
import be.grys.scoresabersomething.Models.Friends.FriendList;
import be.grys.scoresabersomething.Models.Friends.FriendsSharedPref;
import be.grys.scoresabersomething.Models.Friends.Testing;
import be.grys.scoresabersomething.Models.PlayerProfile.Player;
import be.grys.scoresabersomething.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class LeaderBoardFriendAdapter extends RecyclerView.Adapter<LeaderBoardFriendAdapter.ViewHolder> {
    private Context context;
    FragmentActivity activity;
    ArrayList<Testing> testings = new ArrayList<>();

    private int position;
    private int count;

    public LeaderBoardFriendAdapter(FragmentActivity context){
        this.activity = context; ;
    }

    public int getPosition() {
        return position;
    }
    public void setPosition(int position) {
        this.position = position;
    }
    public String getData(int position){
        return testings.get(position).getFriendsSharedPref().getId();
    }
    public void setData(FriendList friendList) {

        if (friendList.getFriends() != null) {
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
        sortList();
    }
    public void RemovePlayer(int position){
        Log.d(TAG, "RemovePlayer: "+ testings.get(position).toString());
        testings.remove(position);
    }
    public void sortList(){

        Collections.sort(testings, new Comparator<Testing>() {
            @Override
            public int compare(Testing o1, Testing o2) {
                return o1.getlPlayer().getPlayer_info().getRank().compareTo(o2.getlPlayer().getPlayer_info().getRank());
            }
        });


        SaveFriendList();

        notifyDataSetChanged();

    }
    private void SaveFriendList() {
        FriendList friendList = new FriendList();
        for( Testing pl: testings)
        {
            if (friendList.getFriends() != null) {
                friendList.getFriends().add(pl.getFriendsSharedPref());
            } else{
                List<FriendsSharedPref> friends = new ArrayList<>();
                friends.add(pl.getFriendsSharedPref());
                friendList.setFriends(friends);
            }
        }
        Log.d(TAG, "sortList:");
        Gson gson = new Gson();
        String json = gson.toJson(friendList);
        Log.d(TAG, "onResponse: json: " + json);
        SharedPreferences sharedPref =  activity.getPreferences(activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("playerFriends", json);
        editor.apply();
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

        //TODO: change placeholder
        Glide.with(context)
                .load("https://new.scoresaber.com" + testings.get(position).getFriendsSharedPref().getAvatar())
                .into(holder.avatar);


        if (testings.get(position).getlPlayer() == null) {


            Call<Player> playerCall = ApiClient.getPlayerService().getUserInfo(testings.get(position).getFriendsSharedPref().getId());
            playerCall.enqueue(new Callback<Player>() {


                @Override
                public void onResponse(Call<Player> call, Response<Player> response) {
                    if (!response.isSuccessful()) {
                        Log.d(TAG, "onResponse: isSuccessful: " + response.code());
                        return;
                    }


                    Player player = response.body();

                    testings.get(position).setlPlayer(player);
                    setItem(holder, player);
                    Log.d(TAG, "onResponse: position " + position + " / "+ testings.size());
                    if(count == testings.size()-1){
                        sortList();
                    } else {
                        count++;
                    }
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
                .into(holder.flag);

        String historyString = lPlayer.getPlayer_info().getHistory();
        String[] stringTokens = historyString.split(",");
        int size = stringTokens.length;
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = Integer.parseInt(stringTokens[i]);
        }
        int historyDiff;
        if (arr.length < 7) {
            historyDiff = arr[size - (size - 1)] - lPlayer.getPlayer_info().getRank();
        } else {
            historyDiff = arr[size - 7] - lPlayer.getPlayer_info().getRank();
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
