package utils;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 */
public class PasswordEncrypterTest {

    @Test
    public void testPasswordOf_user1() {
        String sha256User1 = "0a041b9462caa4a31bac3567e0b6e6fd9100787db2ab433d96f6d178cabfce90";
        assertThat(new PasswordEncrypter().encrypt("user1"), is(sha256User1));

    }

}
