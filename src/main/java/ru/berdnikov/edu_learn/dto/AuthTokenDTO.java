package ru.berdnikov.edu_learn.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthTokenDTO {
    private String token;
}
