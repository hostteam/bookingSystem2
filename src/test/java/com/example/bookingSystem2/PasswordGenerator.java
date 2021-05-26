package com.example.bookingSystem2;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {

    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String plainPassword = "123456";
        String encodedPassword = passwordEncoder.encode(plainPassword);

        System.out.println(encodedPassword);
    }

}
