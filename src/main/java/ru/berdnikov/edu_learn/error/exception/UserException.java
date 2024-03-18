package ru.berdnikov.edu_learn.error.exception;

import org.springframework.security.core.AuthenticationException;

public class UserException extends AuthenticationException {
    public UserException(String message) {
        super(message);
    }
}
