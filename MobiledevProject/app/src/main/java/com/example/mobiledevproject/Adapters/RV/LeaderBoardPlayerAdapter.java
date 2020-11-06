package com.example.mobiledevproject.Adapters.RV;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mobiledevproject.Models.LeaderboardPlayer.LPlayer;
import com.example.mobiledevproject.Models.LeaderboardPlayer.LeaderboardPlayers;
import com.example.mobiledevproject.R;

public class LeaderBoardPlayerAdapter extends RecyclerView.Adapter<LeaderBoardPlayerAdapter.ViewHolder> {
    LeaderboardPlayers leaderboardPlayers;
    private Context context;

    public void setData(LeaderboardPlayers leaderboardPlayers) {
        this.leaderboardPlayers = leaderboardPlayers;
    }

    public void addData(LeaderboardPlayers leaderboardPlayers) {
        this.leaderboardPlayers.getPlayers().addAll(leaderboardPlayers.getPlayers());
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_leaderboard_player, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LPlayer player = leaderboardPlayers.getPlayers().get(position);

        holder.playerName.setText(player.getPlayerName());
        holder.playerRank.setText("#"+player.getRank());
        holder.playerpp.setText(player.getPp()+"pp");

        // TODO: Update placeholder


        Glide.with(context)
                .load("https://new.scoresaber.com" + player.getAvatar())
                .placeholder(R.drawable.about)
                .error(R.drawable.leaderbord)
                .into(holder.avatar);

        // TODO: Update placeholder


        Glide.with(context)
                .load("https://new.scoresaber.com/api/static/flags/" + player.getCountry().toLowerCase()+ ".png")
                .placeholder(R.drawable.about)
                .error(R.drawable.leaderbord)
                .into(holder.flag);

        if (player.getDifference() > 0){
            holder.linearLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.leaderboardUp));
        } else if (player.getDifference() == 0){
            holder.linearLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.greyText));
        } else {
            holder.linearLayout.setBackgroundColor(ContextCompat.getColor(context, R.color.leaderboardDown));
        }
    }

    @Override
    public int getItemCount() {
        if (leaderboardPlayers == null){
            return 0;
        } else {
            return leaderboardPlayers.getPlayers().size();
        }
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView avatar, flag;
        TextView playerName, playerpp, playerRank;
        LinearLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            avatar = itemView.findViewById(R.id.leaderboard_ImagePlayer);
            flag = itemView.findViewById(R.id.leaderboard_Local_Flag);

            playerName = itemView.findViewById(R.id.leaderboard_PlayerName);
            playerpp = itemView.findViewById(R.id.leaderboard_Pp);
            playerRank = itemView.findViewById(R.id.leaderboard_Position);

            linearLayout = itemView.findViewById(R.id.leaderboard_Linear);

        }
    }
}
