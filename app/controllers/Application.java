package controllers;

import auth.LoginRequest;
import auth.NeedLogin;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;
import play.*;
import play.data.Form;
import play.data.validation.ValidationError;
import play.libs.Json;
import play.mvc.*;


import services.LoginService;
import views.html.*;

import java.util.Arrays;
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


    // AJAXメソッド
    @BodyParser.Of(BodyParser.Json.class) //JSON以外を拒否
    public Result tryLogin() {

        JsonNode node = request().body().asJson();
        Form<LoginRequest> form = Form.form(LoginRequest.class).bind(node);

        if(form.hasErrors()) {
            return badRequest(form.errorsAsJson());
        }

        LoginRequest lr = Json.fromJson(node, LoginRequest.class);

        if(service.login(lr.getId(), lr.getPassword())) {
            return ok("OK. valid user");
        } else {
            return badRequest("invalid id or password");
        }

    }

    /**
     *  AJAX専用のコントローラメソッドを定義し、viewでのURL指定のミスを検出する。
     */
    public Result jsRoutes() {
        return ok(
                Routes.javascriptRouter("jsRoutes", routes.javascript.Application.tryLogin())
        );
    }
}
