package controllers;

import models.Neighbor;
import play.data.validation.Constraints;

public class LoginForm {

    @Constraints.Required(message = "This field is required")
    protected String username;

    @Constraints.Required(message = "This field is required")
    protected String password;

    public LoginForm() {

    }

    public String validate() {
        if (Neighbor.authenticate(username, password) == null) {
            return "Invalid Username or Password";
        }
        return null;
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
}

