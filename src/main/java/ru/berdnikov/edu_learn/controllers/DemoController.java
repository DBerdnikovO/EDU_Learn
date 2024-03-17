package ru.berdnikov.edu_learn.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.berdnikov.edu_learn.entity.Game;
import ru.berdnikov.edu_learn.service.GameService;

import java.util.List;

@RestController
public class DemoController {
    private final GameService gameService;

    public DemoController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/admin")
    public ResponseEntity<String> admin(){
        return ResponseEntity.ok().body("HELLO, ADMIN");
    }

    @GetMapping("/user")
    public ResponseEntity<String> user(){
        return ResponseEntity.ok().body("HELLO, USER");
    }

    @GetMapping("/games")
    private ResponseEntity<List<Game>> games(){
        return ResponseEntity.ok().body(gameService.getAllGames());
    }

    @PostMapping("/game")
    public ResponseEntity<String> addGame(@RequestBody Game game){
        gameService.saveGame(game);
        return ResponseEntity.ok().body("SUCCESS");
    }

    @PutMapping("/game/{id}")
    public ResponseEntity<String> update(@RequestBody Game game, @PathVariable Long id){
        gameService.updateGame(game, id);
        return ResponseEntity.ok().body("SUCCESS");
    }

    @DeleteMapping("/game/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        gameService.deleteGame(id);
        return ResponseEntity.ok().body("SUCCESS");
    }
}
