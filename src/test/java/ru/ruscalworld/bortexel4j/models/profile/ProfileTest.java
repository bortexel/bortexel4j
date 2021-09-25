package ru.ruscalworld.bortexel4j.models.profile;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProfileTest {
    @Test
    void getByUserName() {
        Profile profile = Profile.getByUserName("RuscalWorld").execute();
        assertNotNull(profile);
        assertNotNull(profile.getBanStats());
        assertNotNull(profile.getWarningStats());
        assertEquals("RuscalWorld", profile.getUsername());
    }

    @Test
    void getPunishments() {
        Profile profile = Profile.getByUserName("FuriSky").execute();
        assertNotNull(profile);

        Ban.ProfileBans bans = profile.getBans().execute();
        assertNotNull(bans.getBans());
        assertTrue(bans.getBans().size() > 0);

        Warning.ProfileWarnings warnings = profile.getWarnings().execute();
        assertNotNull(warnings.getWarnings());
        assertTrue(warnings.getWarnings().size() > 0);
    }
}