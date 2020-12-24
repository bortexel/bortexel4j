package ru.ruscalworld.bortexel4j.exceptions;

import ru.ruscalworld.bortexel4j.core.APIError;

public class NotFoundException extends BortexelException {
    public NotFoundException(APIError error, int status) {
        super(error, status);
    }
}
