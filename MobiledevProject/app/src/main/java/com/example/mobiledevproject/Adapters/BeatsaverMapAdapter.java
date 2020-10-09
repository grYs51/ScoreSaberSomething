package com.example.mobiledevproject.Adapters;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.solver.state.State;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mobiledevproject.Models.Beatsaver.MapsBeatsaver;
import com.example.mobiledevproject.Models.Beatsaver.beatsavermap.BeatsaverMap;
import com.example.mobiledevproject.R;
import com.example.mobiledevproject.Shared.DiffColor;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import org.ocpsoft.prettytime.PrettyTime;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.zip.DataFormatException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.content.ContentValues.TAG;

public class BeatsaverMapAdapter extends RecyclerView.Adapter<BeatsaverMapAdapter.ViewHolder> {

    private static DecimalFormat df2 = new DecimalFormat("#");
    private MapsBeatsaver mapsBeatsaver;
    private Context context;
    PrettyTime p;
    Instant i;
    Picasso picasso;

    public void setData(MapsBeatsaver mapsBeatsaver){
        this.mapsBeatsaver = mapsBeatsaver;
        notifyDataSetChanged();
    }
    public void addData(MapsBeatsaver mapsBeatsaver){
        this.mapsBeatsaver.getBeatsaverMaps().addAll(mapsBeatsaver.getBeatsaverMaps());
    }


    @NonNull
    @Override
    public BeatsaverMapAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        p = new PrettyTime();

        return new BeatsaverMapAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_map_beatsaver,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull BeatsaverMapAdapter.ViewHolder holder, int position) {
        BeatsaverMap map = mapsBeatsaver.getBeatsaverMaps().get(position);
        int rating = Integer.parseInt (df2.format(map.getStats().getRating() * 100));

        String dt = map.getUploaded();

        try {
            i = Instant.parse(dt);
            holder.levelAuthorName.setText(map.getMetaData().getLevelAuthorName() + " - " + p.format(Date.from(i)));
        } catch ( DateTimeParseException dtpe){
            Log.d(TAG, "catch: "+ dtpe);
            holder.levelAuthorName.setText(map.getMetaData().getLevelAuthorName());
        }

//        Log.d(TAG, "onBindViewHolder: https://beatsaver.com"+ map.getCoverURL());
        holder.mapTitle.setText(map.getName());
        holder.mapAuthorName.setText(map.getMetaData().getSongAuthorName());
        if(map.getStats().getUpVotes()+map.getStats().getDownVotes() == 0 ){
            holder.mapRating.setText("?");
        } else{
            holder.mapRating.setText("" +  rating);
        }
        if(map.getMetaData().getDifficulties().isEasy()){
            holder.colorDiffEasy.setImageResource(R.color.easy);
        } if (map.getMetaData().getDifficulties().isNormal()){
            holder.colorDiffNormal.setImageResource(R.color.mediums);
        } if (map.getMetaData().getDifficulties().isHard()){
            holder.colorDiffHard.setImageResource(R.color.hard);
        } if (map.getMetaData().getDifficulties().isExpert()){
            holder.colorDiffExpert.setImageResource(R.color.expert);
        } if (map.getMetaData().getDifficulties().isExpertPlus()){
            holder.colorDiffExpertPlus.setImageResource(R.color.expertPlus);
        }

        if(rating >= 65 ){
            holder.mapDiffColor.setImageResource(R.color.easy);
        } else if ( rating < 65){
            holder.mapDiffColor.setImageResource(R.color.hard);
        } else if ( rating < 50){
            holder.mapDiffColor.setImageResource(R.color.expert);
        }

        Glide.with(context)
                .load("https://beatsaver.com"+ map.getCoverURL())
                .placeholder(R.drawable.about)
                .error(R.drawable.leaderbord)
                .into(holder.mapImage);

    }

    @Override
    public int getItemCount(){
        if(mapsBeatsaver == null){
            return 0;
        } else {
            return mapsBeatsaver.getBeatsaverMaps().size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView mapImage, mapDiffColor;
        ImageView colorDiffEasy, colorDiffNormal, colorDiffHard, colorDiffExpert, colorDiffExpertPlus;

        TextView mapTitle, mapAuthorName, levelAuthorName, mapRating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mapImage = itemView.findViewById(R.id.item_Map_image);
            mapDiffColor = itemView.findViewById(R.id.item_mapDiffColor);
            colorDiffEasy = itemView.findViewById(R.id.colorDiffEasy);
            colorDiffNormal = itemView.findViewById(R.id.colorDiffNormal);
            colorDiffHard = itemView.findViewById(R.id.colorDiffHard);;
            colorDiffExpert = itemView.findViewById(R.id.colorDiffExpert);
            colorDiffExpertPlus = itemView.findViewById(R.id.colorDiffExpertPlus);

            mapTitle = itemView.findViewById(R.id.item_songName);
            mapAuthorName = itemView.findViewById(R.id.item_songAuthorName);
            levelAuthorName = itemView.findViewById(R.id.item_levelAuthorName);
            mapRating = itemView.findViewById(R.id.item_mapRating);


            
        }
    }
}
