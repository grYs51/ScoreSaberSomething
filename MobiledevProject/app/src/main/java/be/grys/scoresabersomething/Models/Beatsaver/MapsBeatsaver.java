package be.grys.scoresabersomething.Models.Beatsaver;

import be.grys.scoresabersomething.Models.Beatsaver.beatsavermap.BeatsaverMap;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MapsBeatsaver {

    @SerializedName("docs")
    private List<BeatsaverMap> beatsaverMaps;
    @SerializedName("totalDocs")
    private int total_docs;
    @SerializedName("lastPage")
    private int lastPage;
    @SerializedName("prevPage")
    private int prevPage;
    @SerializedName("nextPage")
    private int nextPage;

    public List<BeatsaverMap> getBeatsaverMaps() {
        return beatsaverMaps;
    }

    public int getTotal_docs() {
        return total_docs;
    }

    public int getLastPage() {
        return lastPage;
    }

    public int getPrevPage() {
        return prevPage;
    }

    public int getNextPage() {
        return nextPage;
    }


}
