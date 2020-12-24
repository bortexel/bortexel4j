package ru.ruscalworld.bortexel4j;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import ru.ruscalworld.bortexel4j.exceptions.LoginException;

public class Bortexel4J {
    public static final String API_URL = "https://api.bortexel.ru/v3";

    private final String token;
    private final String owner;
    private final int level;

    private Bortexel4J(String token, String owner, int level) {
        this.token = token;
        this.owner = owner;
        this.level = level;
    }

    public static Bortexel4J login(String token) throws LoginException {
        return new Bortexel4J(token, null, 3);
        //throw new LoginException("", "", 401);
        /*String result = HttpUtil.performGetRequest(API_URL + "/method/authorization/checkToken.php?token=" + token);
        boolean valid = true;
        if (valid) {
            return new Bortexel4J(token, (String) response.get("owner"), (int) response.get("level"));
        } else throw new LoginException();
        return null;*/
    }

    public Request.Builder getDefaultRequestBuilder() {
        Request.Builder builder = new Request.Builder();
        builder.header("Authorization", "Bearer " + this.token);
        return builder;
    }

    public static OkHttpClient getDefaultHttpClient() {
        return new OkHttpClient();
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
}
