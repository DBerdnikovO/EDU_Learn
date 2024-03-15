package ru.berdnikov.edu_learn.entity;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthToken {
    private String token;
}
