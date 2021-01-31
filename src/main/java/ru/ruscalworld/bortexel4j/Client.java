package ru.ruscalworld.bortexel4j;

import okhttp3.Request;

public interface Client {
    String getApiUrl();
    Request.Builder getDefaultRequestBuilder();
}
