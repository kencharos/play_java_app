package controllers;

import auth.NeedLogin;
import play.*;
import play.mvc.*;


import views.html.*;

public class Application extends Controller {

    public Result index() {
        return redirect(routes.Application.login());
    }

    public Result login() {
        return ok(login.render("login2"));
    }

    public Result doLogin() {
        session("user", "user1");

        return redirect(routes.Application.welcome());
    }
    public Result logout() {
        session().clear();

        return redirect(routes.Application.login());
    }

    @Security.Authenticated(NeedLogin.class)
    public Result welcome() {

        return ok(welcome.render(session().get("user")));
    }

}
