package ru.ruscalworld.bortexel4j.models.user;

import ru.ruscalworld.bortexel4j.Bortexel4J;
import ru.ruscalworld.bortexel4j.core.Action;

public class UserSkin {
    private final User user;
    private final Textures textures;

    public UserSkin(User user, Textures textures) {
        this.user = user;
        this.textures = textures;
    }

    public static Action<UserSkin> getByUserID(int id, Bortexel4J client) {
        Action<UserSkin> action = new Action<>("/users/" + id + "/skin", client);
        action.setType(UserSkin.class);
        return action;
    }

    public User getUser() {
        return user;
    }

    public Textures getTextures() {
        return textures;
    }
}
