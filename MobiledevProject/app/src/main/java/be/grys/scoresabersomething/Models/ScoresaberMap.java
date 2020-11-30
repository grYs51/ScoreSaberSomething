package be.grys.scoresabersomething.Models;

public class ScoresaberMap {

    public int getRank() {
        return rank;
    }

    public int getScoreId() {
        return scoreId;
    }

    public int getScore() {
        return score;
    }

    public int getUnmodififiedScore() {
        return unmodififiedScore;
    }

    public String getMods() {
        return mods;
    }

    public double getPp() {
        return pp;
    }

    public double getWeight() {
        return weight;
    }

    public String getTimeSet() {
        return timeSet;
    }

    public int getLeaderboardId() {
        return leaderboardId;
    }

    public String getSongHash() {
        return songHash;
    }

    public String getSongName() {
        return songName;
    }

    public String getSongSubName() {
        return songSubName;
    }

    public String getSongAuthorName() {
        return songAuthorName;
    }

    public String getLevelAuthorName() {
        return levelAuthorName;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public String getDifficultyRaw() {
        return difficultyRaw;
    }

    public int getMaxScore() {
        return maxScore;
    }


    private int rank;
    private int scoreId;
    private int score;
    private int unmodififiedScore;
    private String mods;
    private double pp;
    private double weight;
    private String timeSet;
    private int leaderboardId;
    private String songHash;
    private String songName;
    private String songSubName;
    private String songAuthorName;
    private String levelAuthorName;
    private int difficulty;
    private String difficultyRaw;
    private int maxScore;

    public ScoresaberMap() {
    }

}
