package ru.berdnikov.edu_learn.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.berdnikov.edu_learn.config.JwtProvider;
import ru.berdnikov.edu_learn.dto.PersonDTO;
import ru.berdnikov.edu_learn.entity.AuthToken;
import ru.berdnikov.edu_learn.entity.Person;
import ru.berdnikov.edu_learn.entity.Role;
import ru.berdnikov.edu_learn.service.PersonService;

import java.util.HashSet;
import java.util.Set;

@RestController
public class AuthController {
    private final PersonService personService;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Autowired
    public AuthController(PersonService personService, PasswordEncoder passwordEncoder, JwtProvider jwtProvider) {
        this.personService = personService;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/reg")
    private ResponseEntity<?> login(@RequestBody PersonDTO personDTO) {
        if(personService.existsPersonByEmailAndUsername(personDTO.getEmail(), personDTO.getUsername())){
            return ResponseEntity.ok().body(new AuthToken("null"));
        } else {
            personService.saveUser(createUser(personDTO));
            String token = jwtProvider.generateToken(personDTO.getEmail());
            return ResponseEntity.ok(AuthToken.builder()
                    .token(token)
                    .build());
        }
    }

    @PostMapping("/auth")
    private ResponseEntity<?> reg(@RequestBody PersonDTO personDTO) {
        if(personService.existsPersonByEmailAndUsername(personDTO.getEmail(), personDTO.getUsername())) {
            UserDetails person = personService.loadUserByUsername(personDTO.getEmail());
            String codePassword = person.getPassword().replaceAll("[\\[\\], ]", ""); ;
            if(passwordEncoder.matches(personDTO.getPassword(), codePassword)){
                String token = jwtProvider.generateToken(person.getUsername());
                return ResponseEntity.ok(AuthToken.builder()
                        .token(token)
                        .build());
            }
        } return ResponseEntity.ok().body(new AuthToken("null"));
    }

    @GetMapping("/admin")
    public ResponseEntity<String> admin(){
        return ResponseEntity.ok().body("HELLO, ADMIN");
    }

    @GetMapping("/user")
    public ResponseEntity<String> user(){
        return ResponseEntity.ok().body("HELLO, USER");
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
