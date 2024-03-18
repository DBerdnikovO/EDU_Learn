package ru.berdnikov.edu_learn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.berdnikov.edu_learn.entity.Audit;
import ru.berdnikov.edu_learn.entity.Game;
import ru.berdnikov.edu_learn.error.ErrorCode;
import ru.berdnikov.edu_learn.error.exception.GameException;
import ru.berdnikov.edu_learn.repository.GameAuditRepository;
import ru.berdnikov.edu_learn.repository.GameRepository;
import ru.berdnikov.edu_learn.service.GameService;

import java.util.Date;
import java.util.List;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final GameAuditRepository gameAuditRepository;
    private final AuditorAware auditorAware;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository, GameAuditRepository gameAuditRepository, AuditorAware auditorAware) {
        this.gameRepository = gameRepository;
        this.gameAuditRepository = gameAuditRepository;
        this.auditorAware = auditorAware;
    }

    @Override
    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    @Override
    @Transactional
    public void saveGameWithAudit(Game game) {
        if(gameExist(game.getName())){
            throw new GameException(ErrorCode.GAME_ALREADY_EXIST.getError());
        } else {
            Audit audit = new Audit();
            game.setAudit(audit);
            this.gameAuditRepository.save(audit);
            this.gameRepository.save(game);
        }
    }

    @Override
    public void deleteGame(Long id) {
        this.gameRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void updateGame(Game game, Long id) {
        Game gameDB = gameRepository.findById(id).orElseThrow(() ->
                new GameException(ErrorCode.GAME_NOT_EXIST.getError()));
        gameDB.setName(game.getName());

        Long auditorId = (Long) auditorAware.getCurrentAuditor().orElse(null);
        Audit audit = gameDB.getAudit();
        audit.setLastModifiedBy(auditorId);
        audit.setLastModifiedDate(new Date());
        gameRepository.save(gameDB);
        gameAuditRepository.save(gameDB.getAudit());
    }

    private Boolean gameExist(String name){
        return gameRepository.existsGameByName(name);
    }
}
