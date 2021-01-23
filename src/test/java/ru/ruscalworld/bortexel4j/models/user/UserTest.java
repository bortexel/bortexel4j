package ru.ruscalworld.bortexel4j.models.user;

import org.junit.jupiter.api.Test;
import ru.ruscalworld.bortexel4j.Bortexel4J;
import ru.ruscalworld.bortexel4j.models.account.Account;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    private static final Bortexel4J client = Bortexel4J.login();

    @Test
    void getByID() {
        User user = User.getByID(1, client).execute();
        assertNotNull(user);
        assertEquals(1, user.getID());
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
        assertEquals(289, bans.getUser().getID());
        assertNotEquals(0, bans.getBans().size());
    }

    @Test
    void getAccount() {
        User.UserAccount userAccount = User.UserAccount.getByUserID(1, client).execute();
        assertNotNull(userAccount);

        User user = userAccount.getUser();
        assertNotNull(user);
        assertEquals(1, user.getID());

        Account account = userAccount.getAccount();
        assertNotNull(account);
        assertEquals(1, account.getID());
    }
}