package ru.berdnikov.edu_learn.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.berdnikov.edu_learn.entity.Role;
import ru.berdnikov.edu_learn.entity.Person;
import ru.berdnikov.edu_learn.repository.PersonRepository;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Person person = personRepository.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Person with email: " + email + " not found"));
        return new org.springframework.security.core.userdetails.User(
                person.getUsername(),
                Arrays.toString(person.getPassword()),
                getAuthorities(person.getRoles())
        );
    }

    @Override
    public Boolean existsPersonByEmailAndUsername(String email, String username) {
        return personRepository.existsPersonByEmailAndUsername(email,username);
    }

    @Override
    public void saveUser(Person person) {
        personRepository.save(person);
    }

    @Override
    public Person loadUserByEmail(String email) {
        return personRepository.findUserByEmail(email).get();
    }

    private Set<GrantedAuthority> getAuthorities(Set<Role> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_"+ role.getName()))
                .collect(Collectors.toSet());
    }
}
