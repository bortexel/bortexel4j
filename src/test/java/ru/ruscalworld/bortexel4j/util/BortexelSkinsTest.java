package ru.ruscalworld.bortexel4j.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BortexelSkinsTest {
    @Test
    void getAvatarURL() {
        assertEquals("https://skins.bortexel.net/avatars/RuscalWorld?overlay", BortexelSkins.getAvatarURL("RuscalWorld", true));
        assertEquals("https://skins.bortexel.net/avatars/RuscalWorld", BortexelSkins.getAvatarURL("RuscalWorld", false));
    }

    @Test
    void getHeadRenderURL() {
        assertEquals("https://skins.bortexel.net/renders/head/RuscalWorld?overlay", BortexelSkins.getHeadRenderURL("RuscalWorld", true));
        assertEquals("https://skins.bortexel.net/renders/head/RuscalWorld", BortexelSkins.getHeadRenderURL("RuscalWorld", false));
    }

    @Test
    void getBodyRenderURL() {
        assertEquals("https://skins.bortexel.net/renders/body/RuscalWorld?overlay", BortexelSkins.getBodyRenderURL("RuscalWorld", true));
        assertEquals("https://skins.bortexel.net/renders/body/RuscalWorld", BortexelSkins.getBodyRenderURL("RuscalWorld", false));
    }
}