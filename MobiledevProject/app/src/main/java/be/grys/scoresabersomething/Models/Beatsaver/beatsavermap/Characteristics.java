package be.grys.scoresabersomething.Models.Beatsaver.beatsavermap;

import java.io.Serializable;

public class Characteristics implements Serializable {

    public DifficultiesSpecs getDifficulties() {
        return difficulties;
    }

    public String getName() {
        return name;
    }

    private DifficultiesSpecs difficulties;
    private String name;

}
