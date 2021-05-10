package ru.ruscalworld.bortexel4j;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public interface Client {
    String getApiUrl();
    Request.Builder getDefaultRequestBuilder();
    OkHttpClient getHttpClient();
    Call createCall(Request request);
}
