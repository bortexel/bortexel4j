package ru.ruscalworld.bortexel4j.util;

public class BortexelCDN {
    public static final String ENDPOINT_URL = "https://cdn.bortexel.ru/";
    public static final String DISCORD_ENDPOINT_URL = "discord/";

    public static String getResourceURL(String path) {
        return ENDPOINT_URL + path + ".png";
    }

    public static String getDiscordIconURL(String icon) {
        return ENDPOINT_URL + DISCORD_ENDPOINT_URL + icon + ".png";
    }
}
