package ru.ruscalworld.bortexel4j.auth;

import ru.ruscalworld.bortexel4j.core.APIError;

public class AuthError {
    private final int code;
    private final String text;

    public AuthError(int code, String text) {
        this.code = code;
        this.text = text;
    }

    public APIError getAPIError() {
        return new APIError(null, this.getText());
    }

    public int getCode() {
        return code;
    }

    public String getText() {
        return text;
    }
}
