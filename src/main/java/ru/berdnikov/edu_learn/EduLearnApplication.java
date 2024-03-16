package ru.berdnikov.edu_learn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.filter.OncePerRequestFilter;

@SpringBootApplication
public class EduLearnApplication {

    public static void main(String[] args) {
        SpringApplication.run(EduLearnApplication.class, args);
    }

    @Bean
    public Logger logger() {return LoggerFactory.getLogger(OncePerRequestFilter.class);}

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
