package com.askme.dao;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Simple BCrypt utility for hashing and checking passwords.
 * All password security logic lives here so other classes just call these methods.
 */
public class PasswordUtil {

    // Hash a plain-text password using BCrypt
    public static String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    // Check if a plain-text password matches a stored BCrypt hash
    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        if (hashedPassword == null || hashedPassword.isEmpty()) {
            return false;
        }
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
