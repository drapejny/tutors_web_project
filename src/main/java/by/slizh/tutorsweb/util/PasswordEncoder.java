package by.slizh.tutorsweb.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncoder {

    public static final String salt = "$2a$10$KEJo0YBFzqFBf0ZnjvrBxu"; //// TODO: 01.09.2021 как хранить соль?

    private PasswordEncoder() {
    }

    public static String encodePasswrord(String password) {
        String hashed = BCrypt.hashpw(password, salt);
        return hashed;
    }
}
