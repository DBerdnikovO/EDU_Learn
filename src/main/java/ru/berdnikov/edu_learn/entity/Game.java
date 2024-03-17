package ru.berdnikov.edu_learn.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import ru.berdnikov.edu_learn.audit.Auditable;

@Getter
@Setter
@Entity
@Table(name = "game")
public class Game extends Auditable<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;
}
