package ru.ruscalworld.bortexel4j.models.warning;

import org.junit.jupiter.api.Test;
import ru.ruscalworld.bortexel4j.Bortexel4J;
import ru.ruscalworld.bortexel4j.core.Action;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WarningTest {
    @Test
    void getAll() {
        List<Warning> warnings = Warning.getAll(Bortexel4J.login()).execute();
        assertNotNull(warnings);
        assertTrue(warnings.size() > 0);
    }

    @Test
    void getByID() {
        Warning warning = Warning.getByID(2, Bortexel4J.login()).execute();
        assertNotNull(warning);
        assertEquals(2, warning.getID());
    }
}