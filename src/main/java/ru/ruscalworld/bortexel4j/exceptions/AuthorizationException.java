package ru.ruscalworld.bortexel4j.exceptions;

import ru.ruscalworld.bortexel4j.core.APIError;

public class AuthorizationException extends BortexelException {
    public AuthorizationException(APIError error, int status) {
        super(error, status);
    }
}
