package ru.ruscalworld.bortexel4j.core;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Response<T> {
    private final int status;
    private final List<APIError> errors;
    private final List<String> warnings;
    private final T response;

    public Response(int status, List<APIError> errors, List<String> warnings, T response) {
        this.status = status;
        this.errors = errors;
        this.warnings = warnings;
        this.response = response;
    }

    public int getStatus() {
        return status;
    }

    public List<APIError> getErrors() {
        return errors == null ? new ArrayList<>() : errors;
    }

    public List<String> getWarnings() {
        return warnings == null ? new ArrayList<>() : warnings;
    }

    @Nullable
    public T getResponse() {
        return response;
    }
}
