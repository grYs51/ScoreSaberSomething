package be.grys.scoresabersomething.Models.LeaderboardPlayer;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LeaderboardPlayers {
    @SerializedName("players")
    private List<LPlayer> players;

    public List<LPlayer> getPlayers() {
        return players;
    }
}
