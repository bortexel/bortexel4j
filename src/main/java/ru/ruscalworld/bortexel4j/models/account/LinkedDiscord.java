package ru.ruscalworld.bortexel4j.models.account;

import com.google.gson.annotations.SerializedName;

public class LinkedDiscord {
    @SerializedName("discord")
    private final String discordTag;

    @SerializedName("discord_id")
    private final String discordID;

    public LinkedDiscord(String discordTag, String discordID) {
        this.discordTag = discordTag;
        this.discordID = discordID;
    }

    public String getDiscordTag() {
        return discordTag;
    }

    public String getDiscordID() {
        return discordID;
    }
}
