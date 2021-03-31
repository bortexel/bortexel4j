package ru.ruscalworld.bortexel4j;

import okhttp3.Request;

public class RuleClient implements Client {
    private String url = "https://cdn.bortexel.ru/rules";

    public void setURL(String url) {
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
