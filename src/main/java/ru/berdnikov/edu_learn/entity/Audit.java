package ru.berdnikov.edu_learn.entity;

import jakarta.persistence.*;
import lombok.Getter;
import ru.berdnikov.edu_learn.audit.Auditable;

@Entity
@Getter
@Table(name = "audit")
public class Audit extends Auditable<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
