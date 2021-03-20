package ru.ruscalworld.bortexel4j.models.economy;

import org.junit.jupiter.api.Test;
import ru.ruscalworld.bortexel4j.Bortexel4J;

import static org.junit.jupiter.api.Assertions.*;

class ReportTest {
    @Test
    void getByID() {
        Report report = Report.getByID(1).execute();
        assertNotNull(report);
        assertEquals(1, report.getID());
    }
}