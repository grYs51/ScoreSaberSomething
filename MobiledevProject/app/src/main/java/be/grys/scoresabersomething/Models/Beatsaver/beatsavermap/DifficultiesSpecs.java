package be.grys.scoresabersomething.Models.Beatsaver.beatsavermap;

import java.io.Serializable;

public class DifficultiesSpecs implements Serializable {

    public SpecificDiffSpec getEasy() {
        return easy;
    }

    public SpecificDiffSpec getNormal() {
        return normal;
    }

    public SpecificDiffSpec getHard() {
        return hard;
    }

    public SpecificDiffSpec getExpert() {
        return expert;
    }

    public SpecificDiffSpec getExpertPlus() {
        return expertPlus;
    }

    private SpecificDiffSpec easy;
    private SpecificDiffSpec normal;
    private SpecificDiffSpec hard;
    private SpecificDiffSpec expert;
    private SpecificDiffSpec expertPlus;


}
