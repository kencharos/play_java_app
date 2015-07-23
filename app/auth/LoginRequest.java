package auth;

import play.data.validation.Constraints;

/**
 * Created by kentaro.maeda on 2015/07/23.
 */
public class LoginRequest {

    @Constraints.Required
    protected String id;

    @Constraints.Required
    protected String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
