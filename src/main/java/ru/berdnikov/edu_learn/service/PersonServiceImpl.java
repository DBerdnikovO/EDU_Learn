package ru.berdnikov.edu_learn.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.berdnikov.edu_learn.dto.PersonDTO;
import ru.berdnikov.edu_learn.entity.Role;
import ru.berdnikov.edu_learn.entity.Person;
import ru.berdnikov.edu_learn.error.ErrorCode;
import ru.berdnikov.edu_learn.repository.PersonRepository;
import ru.berdnikov.edu_learn.service.exception.UserException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository, PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        Person person = personRepository.findUserByEmail(email)
                .orElseThrow(() -> new UserException(ErrorCode.USER_NOT_FOUND.getError()));
        return new org.springframework.security.core.userdetails.User(
                person.getUsername(),
                Arrays.toString(person.getPassword()),
                getAuthorities(person.getRoles())
        );
    }

    @Override
    public void saveUser(PersonDTO person) throws UserException {
        if(!personExist(person)){
            personRepository.save(createPerson(person));
        }
        throw new UserException(ErrorCode.PERSON_ALREADY_EXIST.getError());
    }

    @Override
    public Boolean passwordMatch(String inPassword, String codePassword) {
        return passwordEncoder.matches(inPassword, codePassword);
    }

    private Set<GrantedAuthority> getAuthorities(Set<Role> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_"+ role.getName()))
                .collect(Collectors.toSet());
    }

    private Boolean personExist(PersonDTO person){
        return personRepository.existsPersonByEmailAndUsername(person.getEmail(), person.getUsername());
    }

    private Person createPerson(PersonDTO personDTO) {
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
