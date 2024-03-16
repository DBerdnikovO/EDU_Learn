package ru.berdnikov.edu_learn.service;

import org.springframework.http.ResponseEntity;
import ru.berdnikov.edu_learn.dto.AuthTokenDTO;

public interface ResponseService {
    ResponseEntity<AuthTokenDTO> error(String error);

    ResponseEntity<AuthTokenDTO> success(String error);

    ResponseEntity<String> passwordError();

    ResponseEntity<String> emailError(String email);
}
