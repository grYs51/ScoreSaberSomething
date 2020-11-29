package be.grys.scoresabersomething.Shared;

public class PpWeight {

    private double pp;
    private double weight;

    public PpWeight(double pp, double weight) {
        this.pp = pp;
        this.weight = weight;
    }

    public double getPpWeight() {
        return pp * weight;
    }
}
