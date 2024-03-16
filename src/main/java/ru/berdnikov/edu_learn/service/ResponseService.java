package ru.berdnikov.edu_learn.service;

import org.springframework.http.ResponseEntity;
import ru.berdnikov.edu_learn.entity.AuthToken;

public interface ResponseService {
    ResponseEntity<AuthToken> error(String error);

    ResponseEntity<AuthToken> success(String error);

    ResponseEntity<String> passwordError();

    ResponseEntity<String> emailError(String email);
}
