package ru.berdnikov.edu_learn.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.berdnikov.edu_learn.dto.AuthTokenDTO;
import ru.berdnikov.edu_learn.error.ErrorCode;
import ru.berdnikov.edu_learn.service.ResponseService;

@Service
public class ResponseServiceImpl implements ResponseService {
    @Override
    public ResponseEntity<AuthTokenDTO> error(String error) {
        return ResponseEntity.badRequest().body(new AuthTokenDTO(error));
    }

    @Override
    public ResponseEntity<AuthTokenDTO> success(String token) {
        return ResponseEntity.ok(AuthTokenDTO.builder()
                .token(token)
                .build());
    }

    @Override
    public ResponseEntity<String> passwordError() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ErrorCode.INCORRECT_PASSWORD.getError());
    }

    @Override
    public ResponseEntity<String> emailError(String email) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorCode.USER_NOT_FOUND.getError());
    }
}
