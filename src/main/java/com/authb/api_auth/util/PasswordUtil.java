package com.authb.api_auth.util;

public class PasswordUtil {

    public static Boolean isPasswordValid(String password) {
        // Regex pattern to check the password
        String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*()_+\\-\\[\\]{};':\"\\\\|,.<>\\/?]).{8,}$";

        // Check if the password matches the regex pattern
        return password != null && password.matches(regex);
    }

}
