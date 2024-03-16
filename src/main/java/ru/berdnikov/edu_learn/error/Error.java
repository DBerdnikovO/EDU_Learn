package ru.berdnikov.edu_learn.error;

public enum Error {
    USER("USER NOT FOUND"),
    PASSWORD("WRONG PASSWORD");

    private final String error;

    Error(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
