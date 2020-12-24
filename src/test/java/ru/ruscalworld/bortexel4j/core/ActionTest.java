package ru.ruscalworld.bortexel4j.core;

import org.junit.jupiter.api.Test;
import ru.ruscalworld.bortexel4j.Bortexel4J;
import ru.ruscalworld.bortexel4j.models.user.User;
import ru.ruscalworld.bortexel4j.models.user.UserSkin;

import static org.junit.jupiter.api.Assertions.*;

class ActionTest {
    @Test
    void testRequest() {
        Bortexel4J client = Bortexel4J.login(System.getenv("BORTEXEL_TOKEN"));
        User user = User.getByID(1, client).execute();
        assertNotNull(user);
        assertEquals(1, user.getId());

        UserSkin skin = UserSkin.getByUserID(1, client).execute();
        assertNotNull(skin);
        assertTrue(skin.getTextures().isValid());
        assertNotNull(skin.getTextures().getValue());
    }
}