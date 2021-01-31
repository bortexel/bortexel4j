package ru.ruscalworld.bortexel4j;

import okhttp3.Request;

public class AuthClient implements Client {
    private final static String DEFAULT_AUTH_SERVER_URL = "https://auth.bortexel.ru/v1";
    private final String url;

    public AuthClient() {
        this.url = DEFAULT_AUTH_SERVER_URL;
    }

    public AuthClient(String url) {
        this.url = url;
    }

    @Override
    public String getApiUrl() {
        return this.url;
    }

    @Override
    public Request.Builder getDefaultRequestBuilder() {
        return new Request.Builder();
    }
}
