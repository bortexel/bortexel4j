package ru.ruscalworld.bortexel4j.auth;

import com.google.gson.annotations.SerializedName;
import ru.ruscalworld.bortexel4j.AuthClient;
import ru.ruscalworld.bortexel4j.core.Action;
import ru.ruscalworld.bortexel4j.core.HTTPMethod;

import java.sql.Timestamp;

public class Session {
    private final String token;

    @SerializedName("account_id")
    private final int accountID;

    @SerializedName("expires_at")
    private final Timestamp expiresAt;

    public Session(String token, int accountID, Timestamp expiresAt) {
        this.token = token;
        this.accountID = accountID;
        this.expiresAt = expiresAt;
    }

    public static Action<Session> loginByName(String username, String password, AuthClient client) {
        Action<Session> action = new Action<>("/login/username", client);
        action.setResponseHandler(new AuthResponseHandler<>());
        action.setResponseType(Session.class);
        action.setMethod(HTTPMethod.POST);
        action.setBody(new LoginRequest(username, password));
        return action;
    }

    public static class LoginRequest {
        private final String username;
        private final String password;

        public LoginRequest(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }
    }

    public String getToken() {
        return token;
    }

    public int getAccountID() {
        return accountID;
    }

    public Timestamp getExpiresAt() {
        return expiresAt;
    }
}
