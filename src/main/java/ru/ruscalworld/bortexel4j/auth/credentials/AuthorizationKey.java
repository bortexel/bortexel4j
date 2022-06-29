package ru.ruscalworld.bortexel4j.auth.credentials;

import okhttp3.Request;

public abstract class AuthorizationKey implements ApiCredentials {
    @Override
    public Request.Builder fillRequest(Request.Builder builder) {
        return builder.header("Authorization", String.format("%s %s", this.getKeyType(), this.getAuthorizationKey()));
    }

    public abstract String getKeyType();

    public static AuthorizationKey fromString(String input) {
        String[] parts = input.split(" ");
        if (parts.length != 2) return new LegacyToken(input);

        String typeName = parts[0];
        String key = parts[1];

        switch (typeName) {
            case LegacyToken.TYPE:
                return new LegacyToken(key);
            case BearerToken.TYPE:
                return new BearerToken(key);
            default:
                return null;
        }
    }
}
