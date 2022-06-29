package ru.ruscalworld.bortexel4j.auth.credentials;

public class BearerToken extends AuthorizationKey {
    public static final String TYPE = "Bearer";

    private final String key;

    public BearerToken(String key) {
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
