package be.grys.scoresabersomething.Models.Beatsaver.beatsavermap;

import java.io.Serializable;

public class Difficulties implements Serializable {
    public boolean isEasy() {
        return easy;
    }

    public boolean isNormal() {
        return normal;
    }

    public boolean isHard() {
        return hard;
    }

    public boolean isExpert() {
        return expert;
    }

    public boolean isExpertPlus() {
        return expertPlus;
    }

    private boolean easy;
    private boolean normal;
    private boolean hard;
    private boolean expert;
    private boolean expertPlus;
}
