package ru.berdnikov.edu_learn.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.berdnikov.edu_learn.dto.PersonDTO;
import ru.berdnikov.edu_learn.security.PersonDetails;
import ru.berdnikov.edu_learn.error.exception.UserException;


public interface PersonService extends UserDetailsService {
    PersonDetails loadUserByUsername(String email) throws UserException;

    void saveUser(PersonDTO person) throws UserException;

    Boolean passwordMatch(String inPassword, String codePassword);
}
