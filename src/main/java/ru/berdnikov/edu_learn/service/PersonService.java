package ru.berdnikov.edu_learn.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.berdnikov.edu_learn.entity.Person;

import java.util.Optional;

public interface PersonService extends UserDetailsService {
    UserDetails loadUserByUsername(String email);

    Boolean existsPersonByEmailAndUsername(String email, String username);

    void saveUser(Person person);

    Person loadUserByEmail(String email);
}
