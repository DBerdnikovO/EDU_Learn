package ru.berdnikov.edu_learn.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.berdnikov.edu_learn.dto.PersonDTO;
import ru.berdnikov.edu_learn.service.exception.UserException;


public interface PersonService extends UserDetailsService {
    UserDetails loadUserByUsername(String email);

    void saveUser(PersonDTO person) throws UserException;

    Boolean passwordMatch(String inPassword, String codePassword);
}
