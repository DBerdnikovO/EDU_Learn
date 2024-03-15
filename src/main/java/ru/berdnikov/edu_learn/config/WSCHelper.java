package ru.berdnikov.edu_learn.config;

import org.springframework.stereotype.Component;

@Component
public class WSCHelper {
    protected enum Roles {
        ADMIN,USER;
    }
    protected enum Resources {
        LOGIN("/login/**"),
        REGIS("/reg/**"),
        ADMIN("/admin/**"),
        PUB("/public/**");

        Resources(String res) {
        }
    }
}
