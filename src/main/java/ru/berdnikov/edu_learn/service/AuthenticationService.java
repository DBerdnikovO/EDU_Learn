package ru.berdnikov.edu_learn.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import ru.berdnikov.edu_learn.dto.PersonDTO;

public interface AuthenticationService {
    ResponseEntity<?> reg(@RequestBody PersonDTO personDTO);

    ResponseEntity<?> login(@RequestBody PersonDTO personDTO);
}
