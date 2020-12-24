package ru.ruscalworld.bortexel4j.core;

public interface Callback<T> {
    void handle(T response);
}
