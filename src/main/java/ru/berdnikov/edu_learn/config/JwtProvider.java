package ru.berdnikov.edu_learn.config;

import io.jsonwebtoken.Claims;

import java.util.Date;

public interface JwtProvider {
    String getEmailFromToken(String token);

    Date getExpirationDateFromToken(String token);

    Claims getAllClaimsFromToken(String token);

    String generateToken(String email);

    Boolean validateToken(String token, String tokenEmail);
}
