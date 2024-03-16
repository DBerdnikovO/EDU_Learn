package ru.berdnikov.edu_learn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.berdnikov.edu_learn.config.JwtProvider;
import ru.berdnikov.edu_learn.dto.PersonDTO;
import ru.berdnikov.edu_learn.entity.AuthToken;
import ru.berdnikov.edu_learn.entity.Person;
import ru.berdnikov.edu_learn.entity.Role;
import ru.berdnikov.edu_learn.error.Error;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final PersonService personService;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final ResponseService responseService;

    @Autowired
    public AuthenticationServiceImpl(PersonService personService, PasswordEncoder passwordEncoder, JwtProvider jwtProvider, ResponseService responseService) {
        this.personService = personService;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
        this.responseService = responseService;
    }

    @Override
    public ResponseEntity<AuthToken> reg(PersonDTO personDTO) {
        if(personExist(personDTO)){
            return responseService.error(Error.USER.getError());
        } else {
            personService.saveUser(createUser(personDTO));
            String token = jwtProvider.generateToken(personDTO.getEmail());
            return responseService.success(token);
        }
    }

    @Override
    public ResponseEntity<?> login(PersonDTO personDTO) {
        Optional<UserDetails> optionalPerson = Optional.ofNullable(personService.loadUserByUsername(personDTO.getEmail()));
        return optionalPerson.map(person -> {
            String codePassword = person.getPassword().replaceAll("[\\[\\], ]", "");
            if (passwordEncoder.matches(personDTO.getPassword(), codePassword)) {
                String token = jwtProvider.generateToken(personDTO.getEmail());
                return responseService.success(token);
            } else {
                return responseService.passwordError();
            }
        }).orElse(responseService.emailError(personDTO.getEmail()));
    }

    private Boolean personExist(PersonDTO personDTO){
        return personService.existsPersonByEmailAndUsername(personDTO.getEmail(), personDTO.getUsername());
    }

    private Person createUser(PersonDTO personDTO) {
        Set<Role> roleSet = new HashSet<>();
        Role role = new Role();
        role.setId(2);
        roleSet.add(role);
        return new Person(
                personDTO.getUsername(),
                personDTO.getEmail(),
                passwordEncoder.encode(personDTO.getPassword()).toCharArray(),
                roleSet);
    }
}
