package ru.berdnikov.edu_learn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.berdnikov.edu_learn.config.JwtProvider;
import ru.berdnikov.edu_learn.dto.PersonDTO;
import ru.berdnikov.edu_learn.dto.AuthTokenDTO;
import ru.berdnikov.edu_learn.security.PersonDetails;
import ru.berdnikov.edu_learn.service.AuthenticationService;
import ru.berdnikov.edu_learn.service.PersonService;
import ru.berdnikov.edu_learn.service.ResponseService;
import ru.berdnikov.edu_learn.service.exception.UserException;

import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final PersonService personService;
    private final JwtProvider jwtProvider;
    private final ResponseService responseService;

    @Autowired
    public AuthenticationServiceImpl(PersonService personService, JwtProvider jwtProvider, ResponseService responseService) {
        this.personService = personService;
        this.jwtProvider = jwtProvider;
        this.responseService = responseService;
    }

    @Override
    public ResponseEntity<AuthTokenDTO> reg(PersonDTO personDTO){
        try {
            personService.saveUser(personDTO);
            String token = jwtProvider.generateToken(personDTO.getEmail());
            return responseService.success(token);
        } catch (UserException ex) {
            return responseService.error(ex.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> login(PersonDTO personDTO) {
        Optional<PersonDetails> optionalPerson = Optional.ofNullable(personService.loadUserByUsername(personDTO.getEmail()));
        return optionalPerson.map(person -> {
            String codePassword = person.getPassword().replaceAll("[\\[\\], ]", "");
            if (personService.passwordMatch(personDTO.getPassword(), codePassword)) {
                String token = jwtProvider.generateToken(personDTO.getEmail());
                return responseService.success(token);
            } else {
                return responseService.passwordError();
            }
        }).orElse(responseService.emailError(personDTO.getEmail()));
    }
}
