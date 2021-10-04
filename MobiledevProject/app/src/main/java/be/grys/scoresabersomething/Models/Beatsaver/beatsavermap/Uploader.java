package be.grys.scoresabersomething.Models.Beatsaver.beatsavermap;

import java.io.Serializable;

public class Uploader implements Serializable {

    private int id;
    private String name;
    private boolean uniqueSet;
    private String hash;
    private String avatar;
    private String type;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isUniqueSet() {
        return uniqueSet;
    }

    public String getHash() {
        return hash;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getType() {
        return type;
    }

}
