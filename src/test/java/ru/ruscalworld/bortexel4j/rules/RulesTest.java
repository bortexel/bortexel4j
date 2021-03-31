package ru.ruscalworld.bortexel4j.rules;

import org.junit.jupiter.api.Test;
import ru.ruscalworld.bortexel4j.RuleClient;

import static org.junit.jupiter.api.Assertions.*;

class RulesTest {
    @Test
    void getByName() {
        Rules rules = Rules.getByName("main").execute();
        assertEquals(rules.getName(), "Общие правила");
    }
}