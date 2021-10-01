package ru.ruscalworld.bortexel4j.models.linking;

import org.junit.jupiter.api.Test;
import ru.ruscalworld.bortexel4j.Bortexel4J;

import static org.junit.jupiter.api.Assertions.*;

class LinkingTest {
    @Test
    void create() {
        Linking linking = Linking.create(Bortexel4J.login()).execute();
        assertNotNull(linking);
        assertNotNull(linking.getToken());

        String token = linking.getToken();
        linking = Linking.getByToken(token, Bortexel4J.login()).execute();
        assertNotNull(linking);
        assertEquals(token, linking.getToken());

        Linking.AuthorizeLinkWrapper link = linking.linkDiscord(Bortexel4J.login()).execute();
        assertNotNull(link);
        assertTrue(link.getAuthorizeURL().contains("discord"));
    }
}