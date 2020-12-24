package ru.ruscalworld.bortexel4j.core;

public class APIError {
    private final String field;
    private final String text;

    public APIError(String field, String text) {
        this.field = field;
        this.text = text;
    }

    public String getField() {
        return field;
    }

    public String getText() {
        return text;
    }
}
