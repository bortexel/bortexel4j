package ru.ruscalworld.bortexel4j.models.user;

import com.google.gson.annotations.SerializedName;
import ru.ruscalworld.bortexel4j.Bortexel4J;
import ru.ruscalworld.bortexel4j.core.Action;
import ru.ruscalworld.bortexel4j.core.HTTPMethod;

public class UserSkin {
    private final User user;
    private final Textures textures;

    public UserSkin(User user, Textures textures) {
        this.user = user;
        this.textures = textures;
    }

    public static Action<UserSkin> getByUserID(int id, Bortexel4J client) {
        Action<UserSkin> action = new Action<>("/users/" + id + "/skin", client);
        action.setResponseType(UserSkin.class);
        return action;
    }

    public static Action<UserSkin> setByUserID(int id, String system, String name, Bortexel4J client) {
        Action<UserSkin> action = new Action<>("/users/" + id + "/skin", client);
        action.setMethod(HTTPMethod.PATCH);
        action.setBody(new SetSkinRequest(system, name));
        action.setResponseType(UserSkin.class);
        return action;
    }

    public User getUser() {
        return user;
    }

    public Textures getTextures() {
        return textures;
    }

    private static class SetSkinRequest {
        @SerializedName(value = "skin_system")
        private final String skinSystem;

        @SerializedName(value = "skin_name")
        private final String skinName;

        private SetSkinRequest(String skinSystem, String skinName) {
            this.skinSystem = skinSystem;
            this.skinName = skinName;
        }

        public String getSkinSystem() {
            return skinSystem;
        }

        public String getSkinName() {
            return skinName;
        }
    }
}
