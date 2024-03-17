package ru.berdnikov.edu_learn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.berdnikov.edu_learn.entity.Person;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person,Long> {
    Optional<Person> findUserByEmail(String email);

    Boolean existsPersonByEmailAndUsername(String email, String username);
}
