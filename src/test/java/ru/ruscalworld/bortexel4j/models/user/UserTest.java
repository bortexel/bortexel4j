package ru.ruscalworld.bortexel4j.models.user;

import org.junit.jupiter.api.Test;
import ru.ruscalworld.bortexel4j.Bortexel4J;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    private static final Bortexel4J client = Bortexel4J.login(System.getenv("BORTEXEL_TOKEN"), false);

    @Test
    void getByID() {
        User user = User.getByID(1, client).execute();
        assertNotNull(user);
        assertEquals(1, user.getId());
    }

    @Test
    void getByUsername() {
        User user = User.getByUsername("_WuscalRorld_", client).execute();
        assertNotNull(user);
        assertEquals("_WuscalRorld_", user.getUsername());
    }

    @Test
    void setSkin() {
        User user = User.getByUsername("_WuscalRorld_", client).execute();
        UserSkin skin = user.setSkin("mojang", "RuscalWorld", client).execute();
        Textures textures = skin.getTextures();

        assertNotNull(skin);
        assertEquals("mojang", skin.getUser().getSkinSystem());
        assertEquals("RuscalWorld", skin.getUser().getSkinName());

        assertNotNull(textures);
        assertTrue(textures.isValid());
        assertNotNull(textures.getValue());
    }

    @Test
    void getSkin() {
        User user = User.getByUsername("_WuscalRorld_", client).execute();
        UserSkin skin = user.getSkin(client).execute();
        Textures textures = skin.getTextures();

        assertNotNull(textures);
    }

    @Test
    void resetSkin() {
        User user = User.getByUsername("_WuscalRorld_", client).execute();
        UserSkin skin = user.resetSkin(client).execute();
        Textures textures = skin.getTextures();

        assertNotNull(skin);
        assertFalse(textures.isValid());
        assertNull(textures.getValue());
    }

    @Test
    void getBans() {
        User.UserBans bans = User.UserBans.getByUserID(289, client).execute();
        assertNotNull(bans);
        assertEquals(289, bans.getUser().getId());
        assertNotEquals(0, bans.getBans().size());
    }
}