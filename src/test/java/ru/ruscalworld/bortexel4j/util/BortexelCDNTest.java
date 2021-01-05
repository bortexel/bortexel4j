package ru.ruscalworld.bortexel4j.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BortexelCDNTest {
    @Test
    void getResourceURL() {
        assertEquals("https://cdn.bortexel.ru/test.png", BortexelCDN.getResourceURL("test"));
    }

    @Test
    void getDiscordIconURL() {
        assertEquals("https://cdn.bortexel.ru/discord/test.png", BortexelCDN.getDiscordIconURL("test"));
    }
}