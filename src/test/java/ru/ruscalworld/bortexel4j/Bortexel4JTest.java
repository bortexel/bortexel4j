package ru.ruscalworld.bortexel4j;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Bortexel4JTest {
    @Test
    void ping() {
        assertDoesNotThrow(Bortexel4J.login().ping()::execute);
    }
}