package be.grys.scoresabersomething.Models.LeaderboardPlayer;

public class LPlayer {


    public String getPlayerId() {
        return playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getRank() {
        return rank;
    }

    public double getPp() {
        return pp;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getCountry() {
        return country;
    }

    public String getHistory() {
        return history;
    }

    public int getDifference() {
        return difference;
    }


    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setPp(double pp) {
        this.pp = pp;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public void setDifference(int difference) {
        this.difference = difference;
    }

    private String playerId;
    private String playerName;
    private int rank;
    private double pp;
    private String avatar;
    private String country;
    private String history;
    private int difference;

}
