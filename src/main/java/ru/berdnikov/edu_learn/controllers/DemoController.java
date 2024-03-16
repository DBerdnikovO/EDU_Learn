package ru.berdnikov.edu_learn.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/admin")
    public ResponseEntity<String> admin(){
        return ResponseEntity.ok().body("HELLO, ADMIN");
    }

    @GetMapping("/user")
    public ResponseEntity<String> user(){
        return ResponseEntity.ok().body("HELLO, USER");
    }
}
