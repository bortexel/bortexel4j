package ru.ruscalworld.bortexel4j;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class AuthClient implements Client {
    private final static String DEFAULT_AUTH_SERVER_URL = "https://auth.bortexel.net/v1";
    private final String url;
    private OkHttpClient httpClient;

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

    @Override
    public OkHttpClient getHttpClient() {
        if (this.httpClient == null) this.httpClient = new OkHttpClient();
        return this.httpClient;
    }

    @Override
    public Call createCall(Request request) {
        return this.getHttpClient().newCall(request);
    }
}
