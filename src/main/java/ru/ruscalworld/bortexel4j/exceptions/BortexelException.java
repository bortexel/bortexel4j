package ru.ruscalworld.bortexel4j.exceptions;

import ru.ruscalworld.bortexel4j.core.APIError;

public class BortexelException extends RuntimeException {
    private final String field;
    private final String message;
    private final int status;

    public BortexelException(String field, String message, int status) {
        this.field = field;
        this.message = message;
        this.status = status;
    }

    public BortexelException(APIError error, int status) {
        this.field = error.getField();
        this.message = error.getText();
        this.status = status;
    }

    public String getField() {
        return field;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }
}
