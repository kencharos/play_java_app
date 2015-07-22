package auth;

import controllers.routes;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;
import play.mvc.Http.Request;

/**
 * 認証済みかどうかを判定するクラス
 *
 * @Authenticatedアノテーションと一緒に用いる
 */
// 他にも認証を行う手段として、 filterや、RequestHander,@Withによるアクション合成などがある。
public class NeedLogin extends Security.Authenticator {

    /** nullを返す場合、未認証とみなされる。 */
    @Override
    public String getUsername(Http.Context ctx) {

        if (ctx.session().containsKey("user")) {

            ctx.args.put("auser", ctx.session().get("user"));
            return ctx.session().get("user");
        } else {
            return null;
        }
    }

    @Override
    public Result onUnauthorized(Http.Context ctx) {
        return redirect(routes.Application.login());
    }
}
