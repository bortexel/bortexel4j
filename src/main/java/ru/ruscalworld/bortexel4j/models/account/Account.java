package ru.ruscalworld.bortexel4j.models.account;

import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import ru.ruscalworld.bortexel4j.Bortexel4J;
import ru.ruscalworld.bortexel4j.core.Action;
import ru.ruscalworld.bortexel4j.models.ban.Ban;
import ru.ruscalworld.bortexel4j.models.warning.Warning;

import java.util.List;

public class Account {
    private final int id;

    @SerializedName("request_id")
    private final int requestID;

    @SerializedName("user_id")
    private final int userID;

    private String email;

    @SerializedName("password_reset")
    private final boolean passwordReset;

    @SerializedName("access_level")
    private int accessLevel;

    @SerializedName("registration_ip")
    private final String registrationIP;

    @SerializedName("authorized_ip")
    private String authorizedIP;

    private final String discord;

    @SerializedName("discord_id")
    private final String discordID;

    public Account(int id, int requestID, int userID, String email, boolean passwordReset, int accessLevel, String registrationIP, String authorizedIP, String discord, String discordID) {
        this.id = id;
        this.requestID = requestID;
        this.userID = userID;
        this.email = email;
        this.passwordReset = passwordReset;
        this.accessLevel = accessLevel;
        this.registrationIP = registrationIP;
        this.authorizedIP = authorizedIP;
        this.discord = discord;
        this.discordID = discordID;
    }

    public static Action<Account> getByID(int id, Bortexel4J client) {
        Action<Account> action = new Action<>("/accounts/" + id, client);
        action.setResponseType(Account.class);
        return action;
    }

    public static Action<List<Account>> getAll(Bortexel4J client) {
        Action<List<Account>> action = new Action<>("/accounts", client);
        action.setResponseType(TypeToken.getParameterized(List.class, Account.class).getType());
        return action;
    }

    public Action<AccountWarnings> getWarnings(Bortexel4J client) {
        Action<AccountWarnings> action = new Action<>("/accounts/" + this.getID() + "/warnings", client);
        action.setResponseType(AccountWarnings.class);
        return action;
    }

    public Action<AccountBans> getBans(Bortexel4J client) {
        Action<AccountBans> action = new Action<>("/accounts/" + this.getID() + "/bans", client);
        action.setResponseType(AccountBans.class);
        return action;
    }

    public static class AccountWarnings {
        private final Account account;
        private final List<Warning> warnings;

        public AccountWarnings(Account account, List<Warning> warnings) {
            this.account = account;
            this.warnings = warnings;
        }

        public Account getAccount() {
            return account;
        }

        public List<Warning> getWarnings() {
            return warnings;
        }
    }

    public static class AccountBans {
        private final Account account;
        private final List<Ban> bans;

        public AccountBans(Account account, List<Ban> bans) {
            this.account = account;
            this.bans = bans;
        }

        public Account getAccount() {
            return account;
        }

        public List<Ban> getBans() {
            return bans;
        }
    }

    public int getID() {
        return id;
    }

    public int getRequestID() {
        return requestID;
    }

    public int getUserID() {
        return userID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isPasswordReset() {
        return passwordReset;
    }

    public int getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }

    public String getRegistrationIP() {
        return registrationIP;
    }

    public String getAuthorizedIP() {
        return authorizedIP;
    }

    public void setAuthorizedIP(String authorizedIP) {
        this.authorizedIP = authorizedIP;
    }

    public String getDiscord() {
        return discord;
    }

    public String getDiscordID() {
        return discordID;
    }
}
