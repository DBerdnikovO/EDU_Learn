package ru.berdnikov.edu_learn.service;

import ru.berdnikov.edu_learn.entity.Game;

import java.util.List;

public interface GameService {
    List<Game> getAllGames();
    void saveGame(Game game);
    void deleteGame(Long id);
    void updateGame(Game game, Long id);
}
