package ru.ruscalworld.bortexel4j.models.profile;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProfileTest {
    @Test
    void getByUserName() {
        Profile profile = Profile.getByUserName("RuscalWorld").execute();
        assertNotNull(profile);
        assertEquals("RuscalWorld", profile.getUsername());
    }
}