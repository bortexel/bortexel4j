package ru.ruscalworld.bortexel4j.auth.credentials;

public class LegacyToken extends AuthorizationKey {
    public static final String TYPE = "Legacy";

    private final String key;

    public LegacyToken(String key) {
        this.key = key;
    }

    @Override
    public String getAuthorizationKey() {
        return this.key;
    }

    @Override
    public String getKeyType() {
        return TYPE;
    }
}
