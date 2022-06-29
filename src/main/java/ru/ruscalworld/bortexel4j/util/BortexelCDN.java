package ru.ruscalworld.bortexel4j.util;

public class BortexelCDN {
    public static final String ENDPOINT_URL = "https://cdn.bortexel.net/";
    private static final String DISCORD_ENDPOINT_URL = "discord/";
    private static final String ITEMS_ENDPOINT_URL = ENDPOINT_URL + "images/items/";

    public static String getResourceURL(String path) {
        return ENDPOINT_URL + path + ".png";
    }

    public static String getDiscordIconURL(String icon) {
        return ENDPOINT_URL + DISCORD_ENDPOINT_URL + icon + ".png";
    }

    public static String getItemIconURL(String name) {
        if (name.startsWith("e_")) name = "enchanted_book";
        return ITEMS_ENDPOINT_URL + name + ".png";
    }
}
