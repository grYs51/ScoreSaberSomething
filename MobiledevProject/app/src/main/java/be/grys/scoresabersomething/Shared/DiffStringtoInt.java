package be.grys.scoresabersomething.Shared;

public class DiffStringtoInt {

    public int difftoint(String diff){

        switch (diff){
            case "_Easy_SoloStandard":
                return 1;
            case "_Normal_SoloStandard":
                return 3;
            case "_Hard_SoloStandard":
                return 5;
            case "_Expert_SoloStandard":
                return 7;
            case "_ExpertPlus_SoloStandard":
                return 9;
            default:
                return 0;

        }
    }
}
