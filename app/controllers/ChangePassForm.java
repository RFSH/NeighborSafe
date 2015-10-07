package controllers;

import models.Neighbor;
import play.data.validation.Constraints;
import play.data.validation.ValidationError;

import java.util.ArrayList;
import java.util.List;

public class ChangePassForm {

    protected String username;

    @Constraints.Required(message = "This field is required")
    protected String old_password;

    @Constraints.Required(message = "This field is required")
    protected String password;

    @Constraints.Required(message = "This field is required")
    protected String re_password;

    public ChangePassForm(){

    }

    public List<ValidationError> validate() {
        List<ValidationError> errors = new ArrayList<ValidationError>();
        if (Neighbor.authenticate(username, old_password) == null) {
            errors.add(new ValidationError("old_password", "Wow, Wrong Password."));
        }
        if(!this.password.equals(this.re_password)){
            errors.add(new ValidationError("re_password", "Password and Confirmation must match."));
        }
        if (this.password.length() < 5) {
            errors.add(new ValidationError("password", "Given password is less than five characters."));
        }
        return errors.isEmpty() ? null : errors;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOld_password() {
        return old_password;
    }

    public void setOld_password(String old_password) {
        this.old_password = old_password;
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
}
