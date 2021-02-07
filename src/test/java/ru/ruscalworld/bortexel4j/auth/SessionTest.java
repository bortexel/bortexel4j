package ru.ruscalworld.bortexel4j.auth;

import ru.ruscalworld.bortexel4j.AuthClient;
import ru.ruscalworld.bortexel4j.exceptions.LoginException;

import static org.junit.jupiter.api.Assertions.*;

class SessionTest {
    void loginByName() {
        assertThrows(LoginException.class, () -> Session.loginByName("RuscalWorld", "What did you expect to see here?..", new AuthClient()).execute());
    }
}