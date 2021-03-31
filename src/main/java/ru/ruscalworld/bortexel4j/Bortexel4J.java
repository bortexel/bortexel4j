package ru.ruscalworld.bortexel4j;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.jetbrains.annotations.Nullable;
import ru.ruscalworld.bortexel4j.core.Action;
import ru.ruscalworld.bortexel4j.exceptions.LoginException;
import ru.ruscalworld.bortexel4j.listening.BroadcastingServer;
import ru.ruscalworld.bortexel4j.models.authorization.AuthCheck;
import ru.ruscalworld.bortexel4j.models.user.User;

public class Bortexel4J implements Client {
    public static final String DEFAULT_API_URL = "https://api.bortexel.ru/v3";

    private final String token;
    private final String owner;
    private final int level;
    private String apiUrl = DEFAULT_API_URL;

    private Bortexel4J(String token, String owner, int level, String apiUrl) {
        this.token = token;
        this.owner = owner;
        this.level = level;
        this.apiUrl = apiUrl;
    }

    public Bortexel4J(String token, String apiUrl) {
        this.token = token;
        this.owner = null;
        this.level = 0;
        this.apiUrl = apiUrl;
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

    public static Bortexel4J anonymous() {
        return new Bortexel4J();
    }

    public static Bortexel4J login(String token) throws LoginException {
        return login(token, DEFAULT_API_URL, true);
    }

    public static Bortexel4J login(String token, String apiUrl) {
        return login(token, apiUrl, true);
    }

    public static Bortexel4J login(String token, String apiUrl, boolean checkAuth) throws LoginException {
        Bortexel4J client = new Bortexel4J(token, apiUrl);
        if (!checkAuth) return client;
        AuthCheck authorization = AuthCheck.checkToken(client).execute();
        if (!authorization.isAuthorized()) throw new LoginException();
        return new Bortexel4J(token, authorization.getUsername(), authorization.getLevel(), apiUrl);
    }

    public static Bortexel4J login() {
        return login(System.getenv("BORTEXEL_TOKEN"), DEFAULT_API_URL, false);
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

    public BroadcastingServer getBroadcastingServer() {
        return this.getBroadcastingServer(null);
    }

    public BroadcastingServer getBroadcastingServer(@Nullable String url) {
        BroadcastingServer server = new BroadcastingServer();
        server.setToken(this.getToken());
        if (url != null) server.setURL(url);
        return server;
    }

    public Action<User> getUserByID(int id) {
        return User.getByID(id, this);
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
