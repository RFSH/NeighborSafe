package controllers;

import models.Neighbor;
import play.data.validation.Constraints;
import play.data.validation.ValidationError;

import java.util.ArrayList;
import java.util.List;

public class SignupForm {

    @Constraints.Required(message = "This field is required")
    protected String username;

    @Constraints.Required(message = "This field is required")
    protected String password;

    @Constraints.Required(message = "This field is required")
    protected String re_password;

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

    public List<ValidationError> validate() {
        List<ValidationError> errors = new ArrayList<ValidationError>();
        if (this.username.length() < 4){
            errors.add(new ValidationError("username", "Given Username is less than 4 characters."));
        }
        if (Neighbor.findByUsername(username) != null) {
            errors.add(new ValidationError("username", "This username is already registered."));
        }
        if (!this.password.equals(this.re_password)) {
            errors.add(new ValidationError("re_password", "Password and Confirmation must match."));
        }
        if (this.password.length() < 5) {
            errors.add(new ValidationError("password", "Given password is less than five characters."));
        }
        return errors.isEmpty() ? null : errors;
    }

    public SignupForm() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRe_password() {
        return re_password;
    }

    public void setRe_password(String re_password) {
        this.re_password = re_password;
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
