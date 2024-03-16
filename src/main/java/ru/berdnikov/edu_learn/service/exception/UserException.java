package ru.berdnikov.edu_learn.service.exception;

import org.springframework.security.core.AuthenticationException;

public class UserException extends AuthenticationException {
    public UserException(String message) {
        super(message);
    }
}
