package controllers;

import auth.LoginRequest;
import auth.NeedLogin;
import com.google.inject.Inject;
import play.*;
import play.data.Form;
import play.data.validation.ValidationError;
import play.mvc.*;


import services.LoginService;
import views.html.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Application extends Controller {

    @Inject private LoginService service;

    public Result login() {
        return ok(login.render(Form.form(LoginRequest.class)));
    }

    public Result doLogin() {

        // 入力値を取得
        Form<LoginRequest> form = Form.form(LoginRequest.class).bindFromRequest(request());

        if (form.hasErrors()) {
            return badRequest(login.render(form));
        }

        if (!service.login(form.get().getId(), form.get().getPassword())) {
            form.errors().put("id", Arrays.asList(new ValidationError("id", "invalid id or password.")));
            return badRequest(login.render(form));
        }


        session("user", form.get().getId());

        return redirect(routes.Application.welcome());
    }
    public Result logout() {
        session().clear();

        return redirect(routes.Application.login());
    }

    @Security.Authenticated(NeedLogin.class)
    public Result welcome() {

        List<String> notifications = Arrays.asList("This is play app sample", "note..");

        return ok(welcome.render(session().get("user"), notifications));
    }

}
