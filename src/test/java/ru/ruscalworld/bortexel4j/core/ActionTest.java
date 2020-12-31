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
        User user = User.getByID(1228, client).execute();
        assertNotNull(user);
        assertEquals(1228, user.getId());

        UserSkin skin = UserSkin.getByUserID(1228, client).execute();
        assertNotNull(skin);

        UserSkin skinByName = UserSkin.getByPlayerName("_WuscalRorld_", client).execute();
        assertNotNull(skinByName);

        user = User.getByUsername("_WuscalRorld_", client).execute();
        assertNotNull(user);
        assertEquals(1228, user.getId());

        skin = user.getSkin(client).execute();
        assertNotNull(skin);

        skin = user.setSkin("mojang", "Test", client).execute();
        assertNotNull(skin);
        assertEquals("Test", skin.getUser().getSkinName());

        skin = user.resetSkin(client).execute();
        assertNotNull(skin);
        assertNull(skin.getUser().getSkinName());
    }
}