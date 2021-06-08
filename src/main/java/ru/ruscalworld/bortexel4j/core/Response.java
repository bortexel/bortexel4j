package ru.ruscalworld.bortexel4j.core;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Response<T> {
    private final int status;
    @Nullable private final List<APIError> errors;
    @Nullable private final List<String> warnings;
    @Nullable private final T response;
    @Nullable private final Meta meta;

    public Response(int status, @Nullable List<APIError> errors, @Nullable List<String> warnings, @Nullable T response, @Nullable Meta meta) {
        this.status = status;
        this.errors = errors;
        this.warnings = warnings;
        this.response = response;
        this.meta = meta;
    }

    public int getStatus() {
        return status;
    }

    public @NotNull List<APIError> getErrors() {
        return errors == null ? new ArrayList<>() : errors;
    }

    public @NotNull List<String> getWarnings() {
        return warnings == null ? new ArrayList<>() : warnings;
    }

    public @Nullable T getResponse() {
        return response;
    }

    public @Nullable Meta getMeta() {
        return meta;
    }

    public static class Meta {
        private final Pagination pagination;

        public Meta(Pagination pagination) {
            this.pagination = pagination;
        }

        public Pagination getPagination() {
            return pagination;
        }
    }
}
