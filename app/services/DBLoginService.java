package services;

import com.google.inject.Inject;
import models.User;
import utils.PasswordEncrypter;

/**
 * Created by kentaro.maeda on 2015/07/23.
 */
public class DBLoginService implements LoginService {

    @Inject private PasswordEncrypter encrypter;

    @Override
    public boolean login(String id, String rawPassword) {
        return User.findIdAndPassword(id, encrypter.encrypt(rawPassword)) != null;
    }
}
