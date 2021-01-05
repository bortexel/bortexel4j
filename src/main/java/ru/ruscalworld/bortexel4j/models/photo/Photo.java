package ru.ruscalworld.bortexel4j.models.photo;

import com.google.gson.reflect.TypeToken;
import ru.ruscalworld.bortexel4j.Bortexel4J;
import ru.ruscalworld.bortexel4j.core.Action;

import java.util.List;

public class Photo {
    private final int id;
    private String url;
    private String description;
    private String author;
    private int season;

    public Photo(int id, String url, String description, String author, int season) {
        this.id = id;
        this.url = url;
        this.description = description;
        this.author = author;
        this.season = season;
    }

    public static Action<Photo> getByID(int id) {
        return getByID(id, new Bortexel4J());
    }

    public static Action<Photo> getByID(int id, Bortexel4J client) {
        Action<Photo> action = new Action<>("/photos/" + id, client);
        action.setResponseType(Photo.class);
        return action;
    }

    public static Action<List<Photo>> getAll() {
        return getAll(new Bortexel4J());
    }

    public static Action<List<Photo>> getAll(Bortexel4J client) {
        Action<List<Photo>> action = new Action<>("/photos", client);
        action.setResponseType(TypeToken.getParameterized(List.class, Photo.class).getType());
        return action;
    }

    public int getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }
}
