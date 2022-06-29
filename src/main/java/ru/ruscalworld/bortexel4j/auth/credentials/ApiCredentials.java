package ru.ruscalworld.bortexel4j.auth.credentials;

import okhttp3.Request;

public interface ApiCredentials {
    String getAuthorizationKey();
    Request.Builder fillRequest(Request.Builder builder);
}
