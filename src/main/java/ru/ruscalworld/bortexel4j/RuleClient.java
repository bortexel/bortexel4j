package ru.ruscalworld.bortexel4j;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class RuleClient implements Client {
    private String url = "https://cdn.bortexel.ru/rules";
    private OkHttpClient httpClient;

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
