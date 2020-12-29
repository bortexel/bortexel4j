package ru.ruscalworld.bortexel4j.models.authorization;

import ru.ruscalworld.bortexel4j.Bortexel4J;
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

    public static Action<AuthCheck> checkToken(String token) {
        Action<AuthCheck> action = new Action<>("/authorization", new Bortexel4J(token));
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
