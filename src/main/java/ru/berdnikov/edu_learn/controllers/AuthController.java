package ru.berdnikov.edu_learn.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.berdnikov.edu_learn.dto.PersonDTO;
import ru.berdnikov.edu_learn.service.AuthenticationService;


@RestController
public class AuthController {
    private final AuthenticationService authenticationService;

    @Autowired
    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    private ResponseEntity<?> login(@RequestBody PersonDTO personDTO) {
        return authenticationService.login(personDTO);
    }

    @PostMapping("/reg")
    private ResponseEntity<?> reg(@RequestBody PersonDTO personDTO) {
        return authenticationService.reg(personDTO);
    }
}
