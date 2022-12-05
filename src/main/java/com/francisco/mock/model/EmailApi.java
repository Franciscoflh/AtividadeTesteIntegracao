package com.francisco.mock.model;

public class EmailApi {
    public static void sendEmail(String email, String msg) {

        if (!email.contains("@")) {
            throw new IllegalArgumentException("O email não é válido");
        }
        System.out.println(email + " notificado.");
    }
}
