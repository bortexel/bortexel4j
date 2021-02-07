package ru.ruscalworld.bortexel4j.core;

import org.junit.jupiter.api.Test;
import ru.ruscalworld.bortexel4j.Bortexel4J;
import ru.ruscalworld.bortexel4j.exceptions.AuthorizationException;
import ru.ruscalworld.bortexel4j.exceptions.LoginException;
import ru.ruscalworld.bortexel4j.models.authorization.AuthCheck;
import ru.ruscalworld.bortexel4j.models.user.User;

import static org.junit.jupiter.api.Assertions.*;

class ActionTest {
    private static final String token = System.getenv("BORTEXEL_TOKEN");

    @Test
    void execute() {
        AuthCheck authorization = AuthCheck.checkToken(token).execute();
        assertNotNull(authorization);
        assertTrue(authorization.isAuthorized());
    }

    private AuthCheck authorization;
    private Exception authException;

    @Test
    void executeAsync() throws InterruptedException {
        AuthCheck.checkToken(token).executeAsync(response -> authorization = response, error -> authException = error);
        Thread.sleep(500);

        assertNotNull(authorization);
        assertNull(authException);
        assertTrue(authorization.isAuthorized());
    }

    private User user;
    private Exception userException;

    @Test
    void executeAsyncError() throws InterruptedException {
        User.getByUsername("a!", Bortexel4J.anonymous()).executeAsync(response -> user = response, error -> userException = error);;
        Thread.sleep(500);

        assertNotNull(userException);
        assertTrue(userException instanceof AuthorizationException);
        assertNull(user);
    }
}