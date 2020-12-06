package be.grys.scoresabersomething.Models.PlayerProfile;

import com.google.gson.annotations.SerializedName;

public class PlayerPlayerInfo {


    @SerializedName("playerId")
    private String player_Id;

    @SerializedName("playerName")
    private String player_Name;

    private String avatar;

    private Integer rank;

    @SerializedName("countryRank")
    private Integer country_Rank;

    private double pp;

    private String country;

    private String history;

    private Integer inactive;

    public String getPlayer_Id() {
        return player_Id;
    }

    public String getPlayer_Name() {
        return player_Name;
    }

    public String getAvatar() {
        return avatar;
    }

    public Integer getRank() {
        return rank;
    }

    public Integer getCountry_Rank() {
        return country_Rank;
    }

    public double getPp() {
        return pp;
    }

    public String getCountry() {
        return country;
    }

    public String getHistory() {
        return history;
    }

    public Integer getInactive() {
        return inactive;
    }

}
