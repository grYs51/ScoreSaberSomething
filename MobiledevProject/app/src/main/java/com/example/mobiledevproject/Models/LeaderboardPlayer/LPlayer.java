package com.example.mobiledevproject.Models.LeaderboardPlayer;

public class LPlayer {

    private String playerId;
    private String playerName;

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

    private int rank;
    private double pp;
    private String avatar;
    private String country;
    private String history;
    private int difference;

}
