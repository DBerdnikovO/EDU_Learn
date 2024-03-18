package ru.berdnikov.edu_learn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.berdnikov.edu_learn.entity.Audit;
import ru.berdnikov.edu_learn.entity.Game;
import ru.berdnikov.edu_learn.repository.GameAuditRepository;
import ru.berdnikov.edu_learn.repository.GameRepository;
import ru.berdnikov.edu_learn.service.GameService;

import java.util.List;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final GameAuditRepository gameAuditRepository;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository, GameAuditRepository gameAuditRepository) {
        this.gameRepository = gameRepository;
        this.gameAuditRepository = gameAuditRepository;
    }

    @Override
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    @Override
    @Transactional
    public void saveGameWithAudit(Game game) {
        Audit audit = new Audit();
        game.setAudit(audit);
        this.gameAuditRepository.save(audit);
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
