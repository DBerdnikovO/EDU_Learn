package ru.berdnikov.edu_learn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.berdnikov.edu_learn.entity.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
}
