package ru.ruscalworld.bortexel4j.models.linking;

import com.google.gson.annotations.SerializedName;
import ru.ruscalworld.bortexel4j.Bortexel4J;
import ru.ruscalworld.bortexel4j.core.Action;
import ru.ruscalworld.bortexel4j.core.HTTPMethod;

public class Linking {
    private final String token;
    private final String username;
    @SerializedName("account_id")
    private final int accountID;
    @SerializedName("minecraft_id")
    private final String minecraftID;
    @SerializedName("discord_tag")
    private final String discordTag;
    @SerializedName("discord_id")
    private final String discordID;
    @SerializedName("ely_id")
    private final int elyID;

    public Linking(String token, String username, int accountID, String minecraftID, String discordTag, String discordID, int elyID) {
        this.token = token;
        this.username = username;
        this.accountID = accountID;
        this.minecraftID = minecraftID;
        this.discordTag = discordTag;
        this.discordID = discordID;
        this.elyID = elyID;
    }

    public static Action<Linking> create(Bortexel4J client) {
        Action<Linking> action = new Action<>("/linking/new", client);
        action.setResponseType(Linking.class);
        action.setMethod(HTTPMethod.POST);
        return action;
    }

    public static Action<Linking> getByToken(String token, Bortexel4J client) {
        Action<Linking> action = new Action<>("/linking/" + token, client);
        action.setResponseType(Linking.class);
        return action;
    }

    public Action<AuthorizeLinkWrapper> linkDiscord(Bortexel4J client) {
        Action<AuthorizeLinkWrapper> action = new Action<>("/linking/" + this.getToken() + "/discord", client);
        action.setResponseType(AuthorizeLinkWrapper.class);
        action.setMethod(HTTPMethod.PUT);
        return action;
    }

    public static class AuthorizeLinkWrapper {
        @SerializedName("authorize_url")
        private final String authorizeURL;

        public AuthorizeLinkWrapper(String authorizeURL) {
            this.authorizeURL = authorizeURL;
        }

        public String getAuthorizeURL() {
            return authorizeURL;
        }
    }

    public String getToken() {
        return token;
    }

    public String getUsername() {
        return username;
    }

    public int getAccountID() {
        return accountID;
    }

    public String getMinecraftID() {
        return minecraftID;
    }

    public String getDiscordTag() {
        return discordTag;
    }

    public String getDiscordID() {
        return discordID;
    }

    public int getElyID() {
        return elyID;
    }
}
