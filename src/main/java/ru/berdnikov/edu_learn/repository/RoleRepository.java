package ru.berdnikov.edu_learn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.berdnikov.edu_learn.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
