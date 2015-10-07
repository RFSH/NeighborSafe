package controllers;

/**
 * For searching the neighbors
 */
public class NeighborQuery {

    // address based
    private String address;

    // ability and supply based
    private boolean cpr;
    private boolean firstAid;
    private String otherMedTrain;
    private boolean haveGenerator;
    private boolean haveFood;
    private String otherMedSupply;
    private String Languages;

    public NeighborQuery() {

    }

    public boolean hasFirstAid() {
        return firstAid;
    }

    public void setFirstAid(boolean firstAid) {
        this.firstAid = firstAid;
    }

    public boolean knowsCpr() {
        return cpr;
    }

    public void setCpr(boolean cpr) {
        this.cpr = cpr;
    }

    public String getOtherMedTrain() {
        return otherMedTrain;
    }

    public void setOtherMedTrain(String otherMed) {
        this.otherMedTrain = otherMed;
    }

    public boolean haveGenerator() {
        return haveGenerator;
    }

    public void setHaveGenerator(boolean haveGenerator) {
        this.haveGenerator = haveGenerator;
    }

    public boolean haveFood() {
        return haveFood;
    }

    public void setHaveFood(boolean haveFood) {
        this.haveFood = haveFood;
    }

    public String getOtherMedSupply() {
        return otherMedSupply;
    }

    public void setOtherMedSupply(String otherMedSupply) {
        this.otherMedSupply = otherMedSupply;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLanguages() {
        return Languages;
    }

    public void setLanguages(String languages) {
        Languages = languages;
    }
}
