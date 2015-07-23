package utils;

import com.google.inject.Singleton;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * パスワード暗号化
 */
@Singleton
public class PasswordEncrypter {

    private static final String ALGORITHM = "SHA-256";

    public String encrypt(String rawPassword){

        MessageDigest md = null;

        try {
            md = MessageDigest.getInstance(ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        byte[] hash = md.digest(rawPassword.getBytes());

        StringBuilder sb = new StringBuilder();
        for (byte b : hash) {
            sb.append(String.format("%02x", b & 0xff));
        }
        return sb.toString();
    }


}
