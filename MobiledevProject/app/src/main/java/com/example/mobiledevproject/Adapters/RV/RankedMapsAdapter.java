package com.example.mobiledevproject.Adapters.RV;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mobiledevproject.Models.RankedMaps.RankedMap;
import com.example.mobiledevproject.Models.RankedMaps.RankedMapsList;
import com.example.mobiledevproject.R;
import com.example.mobiledevproject.Shared.DiffColor;
import com.example.mobiledevproject.Shared.DiffStringtoInt;

public class RankedMapsAdapter extends RecyclerView.Adapter<RankedMapsAdapter.ViewHolder> {

    RankedMapsList rankedMapsList;
    private Context context;


    public void setData(RankedMapsList rankedMapsListresponse) {
    this.rankedMapsList = rankedMapsListresponse;
    }

    public void addData(RankedMapsList rankedMapsListresponse) {
    this.rankedMapsList.getRankedMaps().addAll(rankedMapsListresponse.getRankedMaps());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_rankedmaps, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        RankedMap rankedMap = rankedMapsList.getRankedMaps().get(position);

        DiffStringtoInt diffint = new DiffStringtoInt();
        DiffColor diffColor = new DiffColor(diffint.difftoint(rankedMap.getDiff()));

        holder.item_mapDiffColor.setImageResource(diffColor.getDiffColor());


        holder.item_songName.setText(rankedMap.getName());
        holder.item_songAuthorName.setText(rankedMap.getSongAuthorName());
        holder.item_levelAuthorName.setText(rankedMap.getLevelAuthorName());

        holder.item_plays.setText(rankedMap.getScores());
        holder.item_plays24.setText(rankedMap.getScores_day()+"");
        holder.item_stars.setText(rankedMap.getStars()+"");

        Glide.with(context)
                .load("https://scoresaber.com/" + rankedMap.getImage())
                .placeholder(R.drawable.profile) //has to change
                .error(R.drawable.leaderbord)  // has to change
                .into(holder.item_Map_image);

    }

    @Override
    public int getItemCount() {

        if (rankedMapsList == null) {
            return 0;
        } else {
            return rankedMapsList.getRankedMaps().size();
        }

    }

//    public interface RVClickListener {
//    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView item_Map_image, item_mapDiffColor;
        TextView item_songName, item_songAuthorName, item_levelAuthorName, item_plays, item_plays24,item_stars;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            item_Map_image = itemView.findViewById(R.id.item_Map_image);
            item_mapDiffColor = itemView.findViewById(R.id.item_mapDiffColor);

            item_songName = itemView.findViewById(R.id.item_songName);
            item_songAuthorName = itemView.findViewById(R.id.item_songAuthorName);
            item_levelAuthorName = itemView.findViewById(R.id.item_levelAuthorName);
            item_plays = itemView.findViewById(R.id.item_plays);
            item_plays24 = itemView.findViewById(R.id.item_plays24);
            item_stars = itemView.findViewById(R.id.item_stars);

        }
    }
}
