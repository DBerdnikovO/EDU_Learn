package ru.berdnikov.edu_learn.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.berdnikov.edu_learn.entity.AuthToken;
import ru.berdnikov.edu_learn.error.Error;

@Service
public class ResponseServiceImpl implements ResponseService{
    @Override
    public ResponseEntity<AuthToken> error(String error) {
        return ResponseEntity.badRequest().body(new AuthToken("null"));
    }

    @Override
    public ResponseEntity<AuthToken> success(String token) {
        return ResponseEntity.ok(AuthToken.builder()
                .token(token)
                .build());
    }

    @Override
    public ResponseEntity<String> passwordError() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Error.PASSWORD.getError());
    }

    @Override
    public ResponseEntity<String> emailError(String email) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with email: "+ email +" not found");
    }
}
