package ru.ruscalworld.bortexel4j;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import ru.ruscalworld.bortexel4j.exceptions.LoginException;
import ru.ruscalworld.bortexel4j.models.authorization.AuthCheck;

import java.util.concurrent.TimeUnit;

public class Bortexel4J {
    private final String token;
    private final String owner;
    private final int level;
    private String apiUrl = "https://api.bortexel.ru/v3";

    private Bortexel4J(String token, String owner, int level) {
        this.token = token;
        this.owner = owner;
        this.level = level;
    }

    public Bortexel4J(String token) {
        this.token = token;
        this.owner = null;
        this.level = 0;
    }

    public Bortexel4J() {
        this.token = null;
        this.owner = null;
        this.level = 0;
    }

    public static Bortexel4J login(String token) throws LoginException {
        return login(token, true);
    }

    public static Bortexel4J login(String token, boolean checkAuth) throws LoginException {
        if (!checkAuth) return new Bortexel4J(token);
        AuthCheck authorization = AuthCheck.checkToken(token).execute();
        if (!authorization.isAuthorized()) throw new LoginException();
        return new Bortexel4J(token, authorization.getUsername(), authorization.getLevel());
    }

    public Request.Builder getDefaultRequestBuilder() {
        Request.Builder builder = new Request.Builder();
        if (this.token != null) builder.header("Authorization", "Bearer " + this.token);
        return builder;
    }

    public static OkHttpClient getDefaultHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        return builder.build();
    }

    public static Call createCall(Request request) {
        return getDefaultHttpClient().newCall(request);
    }

    public int getLevel() {
        return level;
    }

    public String getOwner() {
        return owner;
    }

    public String getToken() {
        return token;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }
}
