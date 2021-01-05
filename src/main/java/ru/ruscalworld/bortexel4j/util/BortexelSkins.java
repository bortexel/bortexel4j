package ru.ruscalworld.bortexel4j.util;

public class BortexelSkins {
    public static final String ENDPOINT_URL = "https://skins.bortexel.ru/";

    private static final String AVATAR_ENDPOINT_URL = ENDPOINT_URL + "avatars/";
    private static final String HEAD_RENDER_ENDPOINT_URL = ENDPOINT_URL + "renders/head/";
    private static final String BODY_RENDER_ENDPOINT_URL = ENDPOINT_URL + "renders/body/";

    private static final String OVERLAY_MODIFIER = "?overlay";

    public static String getAvatarURL(String username, boolean overlay) {
        return makeSkinURL(AVATAR_ENDPOINT_URL, username, overlay);
    }

    public static String getHeadRenderURL(String username, boolean overlay) {
        return makeSkinURL(HEAD_RENDER_ENDPOINT_URL, username, overlay);
    }

    public static String getBodyRenderURL(String username, boolean overlay) {
        return makeSkinURL(BODY_RENDER_ENDPOINT_URL, username, overlay);
    }

    private static String makeSkinURL(String endpoint, String username, boolean overlay) {
        return endpoint + username + (overlay ? OVERLAY_MODIFIER : "");
    }
}
