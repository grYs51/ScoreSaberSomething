package com.example.mobiledevproject.Adapters;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mobiledevproject.Models.Scores;
import com.example.mobiledevproject.Models.ScoresaberMap;
import com.example.mobiledevproject.R;
import com.example.mobiledevproject.Shared.DiffColor;
import com.example.mobiledevproject.Shared.PpWeight;
import com.example.mobiledevproject.Shared.ScoresaberAcc;
import com.squareup.picasso.Picasso;

import org.ocpsoft.prettytime.PrettyTime;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.TimeZone;

import static android.content.ContentValues.TAG;

public class ScoresaberMapAdapter extends RecyclerView.Adapter<ScoresaberMapAdapter.ViewHolder> {
    private static DecimalFormat df2 = new DecimalFormat("#.##");
    private Scores scores;
    private Context context;
    PrettyTime p;
    Instant i;

    public ScoresaberMapAdapter(){
    }

    public void setData(Scores scores){
        this.scores = scores;
        notifyDataSetChanged();
    }

    public void addData (Scores scores){
        this.scores.getScores().addAll(scores.getScores());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        p = new PrettyTime();
        return new ScoresaberMapAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_map_scoresaber,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ScoresaberMap scoresaberMap = scores.getScores().get(position);
        PpWeight ppWeight = new PpWeight(scoresaberMap.getPp(),scoresaberMap.getWeight());
        ScoresaberAcc scoresaberAcc = new ScoresaberAcc(scoresaberMap.getScore(),scoresaberMap.getMaxScore());
        DiffColor diffColor = new DiffColor(scoresaberMap.getDifficulty());

        String dt = scoresaberMap.getTimeSet();
        try {
            i = Instant.parse(dt);
            Log.d(TAG, "onBindViewHolder: "+ dt);
            holder.levelAuthor.setText(scoresaberMap.getLevelAuthorName() + " - "+ p.format(Date.from(i)));
        } catch (DateTimeParseException dtpe) {
            Log.d(TAG, "catch: "+ dtpe);
            holder.levelAuthor.setText(scoresaberMap.getLevelAuthorName());
        }


        holder.songName.setText( scoresaberMap.getSongName());
        holder.songAuthor.setText(scoresaberMap.getSongAuthorName());
        holder.rank.setText(""+scoresaberMap.getRank());
        holder.pp.setText(""+scoresaberMap.getPp()+"pp ");
        holder.ppWeight.setText("("+df2.format(ppWeight.getPpWeight()) + "pp)");
        holder.acc.setText(""+ df2.format(scoresaberAcc.getAcc())+"%");

        holder.songdiff.setImageResource(diffColor.getDiffColor());

        Picasso.get()
                .load("https://scoresaber.com/imports/images/songs/"+ scoresaberMap.getSongHash()+".png" )
                .placeholder(R.drawable.profile) //has to change
                .error(R.drawable.leaderbord)  // has to change
                .into(holder.mapImage);
    }

    @Override
    public int getItemCount() {

        if (scores == null){
            return 0;
        } else {
            return scores.getScores().size();
        }


    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView mapImage, songdiff;
        TextView songName, songAuthor, levelAuthor, rank, pp, ppWeight, acc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mapImage = itemView.findViewById(R.id.item_Map_image);
            songdiff = itemView.findViewById(R.id.item_mapDiffColor);

            songName = itemView.findViewById(R.id.item_songName);
            songAuthor = itemView.findViewById(R.id.item_songAuthorName);
            levelAuthor = itemView.findViewById(R.id.item_levelAuthorName);

            rank = itemView.findViewById(R.id.item_rank);
            pp = itemView.findViewById(R.id.item_pp);
            ppWeight = itemView.findViewById(R.id.item_ppWeight);
            acc = itemView.findViewById(R.id.item_Acc);
        }
    }

    public static String convertToNewFormat(String dateStr) throws ParseException {
        TimeZone utc = TimeZone.getTimeZone("UTC");
        SimpleDateFormat sourceFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        SimpleDateFormat destFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sourceFormat.setTimeZone(utc);
        Date convertedDate = sourceFormat.parse(dateStr);
        return destFormat.format(convertedDate);
    }
}
