package ru.berdnikov.edu_learn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.berdnikov.edu_learn.entity.Audit;

@Repository
public interface GameAuditRepository extends JpaRepository<Audit, Long> {
}
