package ru.ruscalworld.bortexel4j.core;

import org.junit.jupiter.api.Test;
import ru.ruscalworld.bortexel4j.models.authorization.AuthCheck;

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

    @Test
    void executeAsync() throws InterruptedException {
        AuthCheck.checkToken(token).executeAsync(response -> authorization = response);
        Thread.sleep(1000);

        assertNotNull(authorization);
        assertTrue(authorization.isAuthorized());
    }
}