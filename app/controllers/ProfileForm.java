package controllers;

import models.Neighbor;
import play.data.validation.Constraints;

public class ProfileForm {

    @Constraints.Required(message = "This field is required")
    protected String homeNumber;

    @Constraints.Required(message = "This field is required")
    protected String zipCode;

    @Constraints.Required(message = "This field is required")
    protected String address;

    protected String medicalCond;

    //Ability Stuff:
    protected boolean cpr;
    protected boolean firstAid;

    protected String otherMedTrainings;

    //Supplies Stuff:
    @Constraints.Required(message = "This field is required")
    protected String utilityGaz;

    @Constraints.Required(message = "This field is required")
    protected String utilityElectric;

    @Constraints.Required(message = "This field is required")
    protected String utilityWater;

    protected boolean haveGenerator;

    protected boolean haveFood;

    protected String otherMedSupplies = "";

    protected String languages = "";

    protected String other = "";

    public static ProfileForm fillForm(Neighbor n) {
        ProfileForm pf = new ProfileForm();
        pf.setAddress(n.profile.address);
        pf.setHomeNumber(n.profile.homeNumber);
        pf.setZipCode(n.profile.zipCode);
        pf.setMedicalCond(n.profile.medicalCond);

        pf.setCpr(n.profile.cpr);
        pf.setFirstAid(n.profile.firstAid);
        pf.setOtherMedTrainings(n.profile.otherMedTrainings);

        pf.setUtilityElectric(n.profile.utilityElectric);
        pf.setUtilityWater(n.profile.utilityWater);
        pf.setUtilityGaz(n.profile.utilityGaz);


        pf.setHaveFood(n.profile.haveFood);
        pf.setHaveGenerator(n.profile.haveGenerator);
        pf.setOtherMedSupplies(n.profile.otherMedSupplies);

        pf.setLanguages(n.profile.languages);
        pf.setOther(n.profile.other);


        return pf;
    }


    public ProfileForm() {
    }

    public String getHomeNumber() {
        return homeNumber;
    }

    public void setHomeNumber(String homeNumber) {
        this.homeNumber = homeNumber;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMedicalCond() {
        return medicalCond;
    }

    public void setMedicalCond(String medicalCond) {
        this.medicalCond = medicalCond;
    }

    public boolean isCpr() {
        return cpr;
    }

    public void setCpr(boolean cpr) {
        this.cpr = cpr;
    }

    public boolean isFirstAid() {
        return firstAid;
    }

    public void setFirstAid(boolean firstAid) {
        this.firstAid = firstAid;
    }

    public String getOtherMedTrainings() {
        return otherMedTrainings;
    }

    public void setOtherMedTrainings(String otherMedTrainings) {
        this.otherMedTrainings = otherMedTrainings;
    }

    public String getUtilityGaz() {
        return utilityGaz;
    }

    public void setUtilityGaz(String utilityGaz) {
        this.utilityGaz = utilityGaz;
    }

    public String getUtilityElectric() {
        return utilityElectric;
    }

    public void setUtilityElectric(String utilityElectric) {
        this.utilityElectric = utilityElectric;
    }

    public String getUtilityWater() {
        return utilityWater;
    }

    public void setUtilityWater(String utilityWater) {
        this.utilityWater = utilityWater;
    }

    public boolean isHaveGenerator() {
        return haveGenerator;
    }

    public void setHaveGenerator(boolean haveGenerator) {
        this.haveGenerator = haveGenerator;
    }

    public boolean isHaveFood() {
        return haveFood;
    }

    public void setHaveFood(boolean haveFood) {
        this.haveFood = haveFood;
    }

    public String getOtherMedSupplies() {
        return otherMedSupplies;
    }

    public void setOtherMedSupplies(String otherMedSupplies) {
        this.otherMedSupplies = otherMedSupplies;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

}
