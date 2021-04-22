package ru.ruscalworld.bortexel4j.models.profile;

import com.google.gson.annotations.SerializedName;
import ru.ruscalworld.bortexel4j.Bortexel4J;
import ru.ruscalworld.bortexel4j.core.Action;

import java.sql.Timestamp;

public class Profile {
    @SerializedName("account_id")
    private final int accountID;

    @SerializedName("user_id")
    private final int userID;

    private final String username;

    @SerializedName("last_login")
    private final Timestamp lastLogin;

    private final long groups;

    public Profile(int accountID, int userID, String username, Timestamp lastLogin, long groups) {
        this.accountID = accountID;
        this.userID = userID;
        this.username = username;
        this.lastLogin = lastLogin;
        this.groups = groups;
    }

    public static Action<Profile> getByUserName(String username) {
        return getByUserName(username, Bortexel4J.anonymous());
    }

    public static Action<Profile> getByUserName(String username, Bortexel4J client) {
        Action<Profile> action = new Action<>("/profiles/" + username, client);
        action.setResponseType(Profile.class);
        return action;
    }

    public int getAccountID() {
        return accountID;
    }

    public int getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public Timestamp getLastLogin() {
        return lastLogin;
    }

    public long getGroups() {
        return groups;
    }
}
