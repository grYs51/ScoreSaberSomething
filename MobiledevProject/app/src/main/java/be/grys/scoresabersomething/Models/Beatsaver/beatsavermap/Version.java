package be.grys.scoresabersomething.Models.Beatsaver.beatsavermap;

import java.util.Date;

public class Version {

    private String hash;
    private String state;
    private Date createdAt;
    private int sageScore;
    private Diff[] Diffs;
    private String downloadURL;
    private String coverURL;
    private String previewURL;


    public String getHash() {
        return hash;
    }

    public String getState() {
        return state;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public int getSageScore() {
        return sageScore;
    }

    public Diff[] getDiffs() {
        return Diffs;
    }

    public String getDownloadURL() {
        return downloadURL;
    }

    public String getCoverURL() {
        return coverURL;
    }

    public String getPreviewURL() {
        return previewURL;
    }
}
