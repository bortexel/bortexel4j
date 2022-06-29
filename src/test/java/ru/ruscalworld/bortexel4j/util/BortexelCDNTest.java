package ru.ruscalworld.bortexel4j.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BortexelCDNTest {
    @Test
    void getResourceURL() {
        assertEquals("https://cdn.bortexel.net/test.png", BortexelCDN.getResourceURL("test"));
    }

    @Test
    void getDiscordIconURL() {
        assertEquals("https://cdn.bortexel.net/discord/test.png", BortexelCDN.getDiscordIconURL("test"));
    }

    @Test
    void getItemIconURL() {
        assertEquals("https://cdn.bortexel.net/images/items/test.png", BortexelCDN.getItemIconURL("test"));
        assertEquals("https://cdn.bortexel.net/images/items/enchanted_book.png", BortexelCDN.getItemIconURL("e_test"));
    }
}