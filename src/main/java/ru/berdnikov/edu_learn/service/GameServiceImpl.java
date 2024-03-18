package ru.berdnikov.edu_learn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.berdnikov.edu_learn.entity.Game;
import ru.berdnikov.edu_learn.repository.GameRepository;

import java.util.List;

@Service
public class GameServiceImpl implements GameService{
    private final GameRepository gameRepository;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    @Override
    public void saveGame(Game game) {
        this.gameRepository.save(game);
    }

    @Override
    public void deleteGame(Long id) {
        this.gameRepository.deleteById(id);
    }

    @Override
    public void updateGame(Game game, Long id) {
        Game gameDB = gameRepository.findById(id).get();
        gameDB.setName(game.getName());
        gameRepository.save(gameDB);
    }
}
