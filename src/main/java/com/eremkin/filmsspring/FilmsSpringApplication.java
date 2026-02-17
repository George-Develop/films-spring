package com.eremkin.filmsspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class FilmsSpringApplication {

    public static void main(String[] args) {
        System.out.println("1234: " + new BCryptPasswordEncoder().encode("1234"));
        System.out.println("4321: " + new BCryptPasswordEncoder().encode("4321"));
        SpringApplication.run(FilmsSpringApplication.class, args);
    }

}
