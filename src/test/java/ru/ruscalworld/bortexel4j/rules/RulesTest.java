package ru.ruscalworld.bortexel4j.rules;

import org.junit.jupiter.api.Test;
import ru.ruscalworld.bortexel4j.RuleClient;

import static org.junit.jupiter.api.Assertions.*;

class RulesTest {
    @Test
    void getByName() {
        Rules rules = Rules.getByName("main").execute();
        assertEquals(rules.getName(), "Общие правила");

        RulePart.Rule rule = rules.findRule("1.1");
        assertNotNull(rule);
        assertNull(rule.getPunishments());
        assertEquals("1.1", rule.getName());

        rule = rules.findRule("1.1.1");
        assertNotNull(rule);
        assertNull(rule.getPunishments());
        assertEquals("1.1.1", rule.getName());

        rule = rules.findRule("2.1");
        assertNotNull(rule);
        assertTrue(rule.getPunishments().size() > 0);
        assertEquals("2.1", rule.getName());
    }
}