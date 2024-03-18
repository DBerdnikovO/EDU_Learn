package ru.berdnikov.edu_learn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.berdnikov.edu_learn.entity.Audit;
import ru.berdnikov.edu_learn.entity.Game;

import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    Optional<Game> getGameByName(String name);
    Boolean existsGameByName(String name);
}
