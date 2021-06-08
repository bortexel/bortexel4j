package ru.ruscalworld.bortexel4j.core;

import org.junit.jupiter.api.Test;
import ru.ruscalworld.bortexel4j.Bortexel4J;
import ru.ruscalworld.bortexel4j.models.warning.Warning;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PaginatedListActionTest {
    @Test
    void fetchAll() {
        List<Warning> warnings = Warning.getAll(Bortexel4J.login()).fetchAll();
        assertNotNull(warnings);
        assertTrue(warnings.size() > 200);
    }
}