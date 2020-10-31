package com.example.mobiledevproject.Adapters.RV;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mobiledevproject.Models.Beatsaver.MapsBeatsaver;
import com.example.mobiledevproject.Models.Beatsaver.beatsavermap.BeatsaverMap;
import com.example.mobiledevproject.R;

import org.ocpsoft.prettytime.PrettyTime;

import java.text.DecimalFormat;
import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.util.Date;

import static android.content.ContentValues.TAG;

public class BeatsaverMapAdapter extends RecyclerView.Adapter<BeatsaverMapAdapter.ViewHolder> {

    private static DecimalFormat df2 = new DecimalFormat("#");
    public MapsBeatsaver mapsBeatsaver;
    private Context context;
    PrettyTime p = new PrettyTime();
    Instant i;
    private RVClickListener rvClickListener;

    public void setData(MapsBeatsaver mapsBeatsaver) {
        this.mapsBeatsaver = mapsBeatsaver;
        notifyDataSetChanged();
    }

    public void addData(MapsBeatsaver mapsBeatsaver) {
        this.mapsBeatsaver.getBeatsaverMaps().addAll(mapsBeatsaver.getBeatsaverMaps());
    }

    public void setlistener(RVClickListener listener) {
        this.rvClickListener = listener;
    }


    @NonNull
    @Override
    public BeatsaverMapAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new BeatsaverMapAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_map_beatsaver, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BeatsaverMapAdapter.ViewHolder holder, int position) {
        BeatsaverMap map = mapsBeatsaver.getBeatsaverMaps().get(position);
        int rating = Integer.parseInt(df2.format(map.getStats().getRating() * 100));

        Logs(position, map);
        setItem(holder, map, rating);

    }


    private void setItem(@NonNull ViewHolder holder, BeatsaverMap map, int rating) {

        String dt = map.getUploaded();

        parseTimeUploaded(holder, map, dt);

        //TODO: ifMap Name is too long
        holder.mapTitle.setText(map.getName());
        holder.mapAuthorName.setText(map.getMetaData().getSongAuthorName());


        setDifficultyImages(holder, map);

        setRating(holder, map, rating);
    // TODO: Update placeholder
        Glide.with(context)
                .load("https://beatsaver.com" + map.getCoverURL())
                .placeholder(R.drawable.about)
                .error(R.drawable.leaderbord)
                .into(holder.mapImage);

    }


    @Override
    public int getItemCount() {
        if (mapsBeatsaver == null) {
            return 0;
        } else {
            return mapsBeatsaver.getBeatsaverMaps().size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView mapImage, mapDiffColor;
        ImageView colorDiffEasy, colorDiffNormal, colorDiffHard, colorDiffExpert, colorDiffExpertPlus;

        TextView mapTitle, mapAuthorName, levelAuthorName, mapRating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            setViews(itemView);
        }

        private void setViews(@NonNull View itemView) {
            itemView.setOnClickListener(this);

            mapImage = itemView.findViewById(R.id.item_Map_image);

            mapDiffColor = itemView.findViewById(R.id.item_mapDiffColor);
            colorDiffEasy = itemView.findViewById(R.id.colorDiffEasy);
            colorDiffNormal = itemView.findViewById(R.id.colorDiffNormal);
            colorDiffHard = itemView.findViewById(R.id.colorDiffHard);
            colorDiffExpert = itemView.findViewById(R.id.colorDiffExpert);
            colorDiffExpertPlus = itemView.findViewById(R.id.colorDiffExpertPlus);
            mapTitle = itemView.findViewById(R.id.item_songName);
            mapAuthorName = itemView.findViewById(R.id.item_songAuthorName);
            levelAuthorName = itemView.findViewById(R.id.item_levelAuthorName);
            mapRating = itemView.findViewById(R.id.item_mapRating);
        }

        @Override
        public void onClick(View v) {
            Log.d(TAG, "onClick: adap" + getAdapterPosition());
            if (rvClickListener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    rvClickListener.onClick(mapsBeatsaver.getBeatsaverMaps().get(getAdapterPosition()));
                }
            }


        }
    }

    private void parseTimeUploaded(@NonNull ViewHolder holder, BeatsaverMap map, String dt) {
        try {
            i = Instant.parse(dt);
            holder.levelAuthorName.setText(map.getMetaData().getLevelAuthorName() + " - " + p.format(Date.from(i)));
        } catch (DateTimeParseException dtpe) {
            Log.d(TAG, "catch: " + dtpe);
            holder.levelAuthorName.setText(map.getMetaData().getLevelAuthorName());
        }
    }

    private void Logs(int position, BeatsaverMap map) {
        Log.d(TAG, "onBindViewHolder: position: " + position);
        Log.d(TAG, "onBindViewHolder: Name: " + map.getName());
        Log.d(TAG, "onBindViewHolder: diff: " + map.getMetaData().getDifficulties().isEasy() + " | " + map.getMetaData().getDifficulties().isNormal() + " | " + map.getMetaData().getDifficulties().isHard() + " | " + map.getMetaData().getDifficulties().isExpert() + " | " + map.getMetaData().getDifficulties().isExpertPlus());
        Log.d(TAG, "onBindViewHolder: ");
    }

    private void setRating(@NonNull ViewHolder holder, BeatsaverMap map, int rating) {
        if (map.getStats().getUpVotes() + map.getStats().getDownVotes() == 0) {
            holder.mapRating.setText("?");
            holder.mapDiffColor.setImageResource(R.color.greyText);
        } else {
            holder.mapRating.setText("" + rating);
            if (65 <= rating) {
                holder.mapDiffColor.setImageResource(R.color.easy);
            } else if (rating >= 50) {
                holder.mapDiffColor.setImageResource(R.color.hard);
            } else if (rating >= 0) {
                holder.mapDiffColor.setImageResource(R.color.expert);
            }
        }

    }

    private void setDifficultyImages(@NonNull ViewHolder holder, BeatsaverMap map) {
        if (map.getMetaData().getDifficulties().isEasy()) {
            holder.colorDiffEasy.setImageResource(R.color.easy);
        } else {
            holder.colorDiffEasy.setImageResource(R.color.greyText);
        }
        if (map.getMetaData().getDifficulties().isNormal()) {
            holder.colorDiffNormal.setImageResource(R.color.mediums);
        } else {
            holder.colorDiffNormal.setImageResource(R.color.greyText);
        }
        if (map.getMetaData().getDifficulties().isHard()) {
            holder.colorDiffHard.setImageResource(R.color.hard);
        } else {
            holder.colorDiffHard.setImageResource(R.color.greyText);
        }
        if (map.getMetaData().getDifficulties().isExpert()) {
            holder.colorDiffExpert.setImageResource(R.color.expert);
        } else {
            holder.colorDiffExpert.setImageResource(R.color.greyText);
        }
        if (map.getMetaData().getDifficulties().isExpertPlus()) {
            holder.colorDiffExpertPlus.setImageResource(R.color.expertPlus);
        } else {
            holder.colorDiffExpertPlus.setImageResource(R.color.greyText);
        }
    }

    public interface RVClickListener {
        void onClick(BeatsaverMap beatsaverMap);
    }
}
