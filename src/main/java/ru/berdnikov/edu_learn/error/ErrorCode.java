package ru.berdnikov.edu_learn.error;

public enum ErrorCode {
    USER_NOT_FOUND("USER NOT FOUND"),
    INCORRECT_PASSWORD("INCORRECT PASSWORD"),
    PERSON_ALREADY_EXIST("PERSON ALREADY EXIST"),
    GAME_ALREADY_EXIST("GAME WITH THIS NAME ALREADY EXISTS"),
    GAME_NOT_EXIST("GAME NTO EXIST");

    private final String error;

    ErrorCode(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
