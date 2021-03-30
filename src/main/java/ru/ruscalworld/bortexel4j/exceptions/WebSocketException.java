package ru.ruscalworld.bortexel4j.exceptions;

public class WebSocketException extends Exception {
    private final int code;
    private final String message;

    public WebSocketException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
