package ru.ruscalworld.bortexel4j.exceptions;

public class LoginException extends BortexelException {
    public LoginException(String field, String message, int status) {
        super(field, message, status);
    }

    public LoginException() {
        super("Authorization", "API authorization failed", 401);
    }
}
