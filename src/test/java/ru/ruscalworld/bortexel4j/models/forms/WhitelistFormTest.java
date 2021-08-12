package ru.ruscalworld.bortexel4j.models.forms;

import org.junit.jupiter.api.Test;
import ru.ruscalworld.bortexel4j.Bortexel4J;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WhitelistFormTest {
    @Test
    void getByID() {
        WhitelistForm form = WhitelistForm.getByID(1, Bortexel4J.login()).execute();
        assertEquals("RuscalWorld", form.getUsername());
    }

    @Test
    void getAll() {
        List<WhitelistForm> forms = WhitelistForm.getAll(Bortexel4J.login()).execute();
        assertTrue(forms.size() > 0);
    }
}