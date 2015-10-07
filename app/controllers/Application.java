package controllers;

import jdk.nashorn.internal.objects.NativeArray;
import models.Neighbor;
import models.NeighborProfile;
import models.People;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import views.html.*;

import static play.data.Form.form;


public class Application extends Controller {

    public static Result login() {
        if (session().get("username") != null) {
            return redirect(routes.Application.searchNeighbors());
        }
        return ok(login.render(form(LoginForm.class)));
    }

    public static Result authenticate() {
        if (session().get("username") != null) {
            return redirect(routes.Application.searchNeighbors());
        }

        Form<LoginForm> loginForm = form(LoginForm.class).bindFromRequest();
        if (loginForm.hasErrors()) {
            return badRequest(login.render(loginForm));
        } else {
            session().clear();
            session("username", loginForm.get().username);
            return redirect(controllers.routes.Application.searchNeighbors());
        }
    }


    public static Result signup() {
        if (session().get("username") != null) {
            return redirect(routes.Application.searchNeighbors());
        }
        return ok(signup.render(form(SignupForm.class)));
    }

    public static Result createNeighbor() {
        if (session().get("username") != null) {
            return redirect(routes.Application.searchNeighbors());
        }

        Form<SignupForm> signupForm = form(SignupForm.class).bindFromRequest();
        if (signupForm.hasErrors()) {
            flash("error", "Fix the errors.");
            return badRequest(signup.render(signupForm));
        } else {
            try {
                NeighborProfile.create(signupForm.get());
            } finally {
                People.addFamilyMembers(form().bindFromRequest(), signupForm.get().username);
            }

            flash("success", "You account has been created. You can now Log in.");
            return redirect(controllers.routes.Application.login());
        }
    }

    @Security.Authenticated(Secured.class)
    public static Result logout() {
        session().clear();
        flash("success", "Be safe, Neighbor. :)");
        return redirect(controllers.routes.Application.login());
    }

    @Security.Authenticated(Secured.class)
    public static Result profile() {

        Form<ProfileForm> profileForm = form(ProfileForm.class);
        profileForm = profileForm.fill(ProfileForm.fillForm(Neighbor.findByUsername(request().username())));
        return ok(profile.render(profileForm, Neighbor.findByUsername(request().username())));
    }

    @Security.Authenticated(Secured.class)
    public static Result updateProfile() {
        Form<ProfileForm> profileForm = form(ProfileForm.class).bindFromRequest();
        if (profileForm.hasErrors()) {
            flash("error", "Fix the errors.");
            return badRequest(profile.render(profileForm, Neighbor.findByUsername(request().username())));
        } else {
            NeighborProfile.update(profileForm.get(), request().username());
            flash("success", "Well, We saved your information. now what?");
            return redirect(controllers.routes.Application.profile());
        }
    }

    @Security.Authenticated(Secured.class)
    public static Result deleteProfile() {
        Neighbor n = Neighbor.findByUsername(request().username());
        n.profile.peopleList.forEach(p -> p.delete());
        n.profile.delete();
        n.delete();
        session().clear();
        flash("success", "Now, YOU'RE NOTHING.");
        return redirect(routes.Application.login());
    }

    @Security.Authenticated(Secured.class)
    public static Result Password() {
        return ok(changepass.render(form(ChangePassForm.class), request().username()));
    }

    @Security.Authenticated(Secured.class)
    public static Result changePassword() {
        Form<ChangePassForm> changePassForm = form(ChangePassForm.class).bindFromRequest();
        if (changePassForm.hasErrors()) {
            flash("error", "Fix the errors.");
            return badRequest(changepass.render(changePassForm, request().username()));
        } else {
            Neighbor.updatePass(changePassForm.get().password, request().username());
            flash("success", "Did you memorize your password? Because We changed it. :|");
            return redirect(controllers.routes.Application.searchNeighbors());
        }
    }

    @Security.Authenticated(Secured.class)
    public static Result searchNeighbors() {
        Neighbor thisNeighbor = Neighbor.findByUsername(request().username());
        Form<NeighborQuery> neighborQuery = form(NeighborQuery.class).bindFromRequest();

        return ok(search.render(NeighborProfile.findNeighbors(neighborQuery.get(), thisNeighbor), request().username(), neighborQuery));
    }

}

