package ru.ruscalworld.bortexel4j.models.authorization;

import ru.ruscalworld.bortexel4j.Bortexel4J;
import ru.ruscalworld.bortexel4j.auth.credentials.ApiCredentials;
import ru.ruscalworld.bortexel4j.auth.credentials.BearerToken;
import ru.ruscalworld.bortexel4j.core.Action;

public class AuthCheck {
    private final boolean authorized;
    private final boolean provided;
    private final boolean valid;
    private final int level;
    private final String username;

    public AuthCheck(boolean authorized, boolean provided, boolean valid, int level, String username) {
        this.authorized = authorized;
        this.provided = provided;
        this.valid = valid;
        this.level = level;
        this.username = username;
    }

    public static Action<AuthCheck> checkToken(ApiCredentials credentials) {
        Action<AuthCheck> action = new Action<>("/authorization", new Bortexel4J(credentials));
        action.setResponseType(AuthCheck.class);
        return action;
    }

    @Deprecated
    public static Action<AuthCheck> checkToken(String token) {
        return checkToken(new BearerToken(token));
    }

    public static Action<AuthCheck> checkToken(Bortexel4J client) {
        Action<AuthCheck> action = new Action<>("/authorization", client);
        action.setResponseType(AuthCheck.class);
        return action;
    }

    public boolean isAuthorized() {
        return authorized;
    }

    public boolean isProvided() {
        return provided;
    }

    public boolean isValid() {
        return valid;
    }

    public int getLevel() {
        return level;
    }

    public String getUsername() {
        return username;
    }
}
