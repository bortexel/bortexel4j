package ru.ruscalworld.bortexel4j.models.photo;

import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.Nullable;
import ru.ruscalworld.bortexel4j.Bortexel4J;
import ru.ruscalworld.bortexel4j.core.Action;
import ru.ruscalworld.bortexel4j.core.PaginatedListAction;

public class Photo {
    private final int id;
    private String url;
    private String description;
    @SerializedName(value = "author_name")
    private String authorName;
    private int season;
    @Nullable
    private final Proxy proxy;

    public Photo(int id, String url, String description, String author, int season, @Nullable Proxy proxy) {
        this.id = id;
        this.url = url;
        this.description = description;
        this.authorName = author;
        this.season = season;
        this.proxy = proxy;
    }

    public static Action<Photo> getByID(int id) {
        return getByID(id, Bortexel4J.anonymous());
    }

    public static Action<Photo> getByID(int id, Bortexel4J client) {
        return getByID(id, false, client);
    }

    public static Action<Photo> getByID(int id, boolean proxy, Bortexel4J client) {
        Action<Photo> action = new Action<>("/photos/" + id + "?proxy=" + proxy, client);
        action.setResponseType(Photo.class);
        return action;
    }

    public static PaginatedListAction<Photo> getAll() {
        return getAll(Bortexel4J.anonymous());
    }

    public static PaginatedListAction<Photo> getAll(Bortexel4J client) {
        return getAll(false, client);
    }

    public static PaginatedListAction<Photo> getAll(boolean proxy, Bortexel4J client) {
        PaginatedListAction<Photo> action = new PaginatedListAction<>("/photos?proxy=" + proxy, client);
        action.setResponseListType(Photo.class);
        return action;
    }

    public static class Proxy {
        private final String originalURL;
        private final String smallURL;
        private final String mediumURL;
        private final String largeURL;

        public Proxy(String originalURL, String smallURL, String mediumURL, String largeURL) {
            this.originalURL = originalURL;
            this.smallURL = smallURL;
            this.mediumURL = mediumURL;
            this.largeURL = largeURL;
        }

        public String getOriginalURL() {
            return originalURL;
        }

        public String getSmallURL() {
            return smallURL;
        }

        public String getMediumURL() {
            return mediumURL;
        }

        public String getLargeURL() {
            return largeURL;
        }
    }

    public int getID() {
        return id;
    }

    public String getURL() {
        return url;
    }

    public void setURL(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public @Nullable Proxy getProxy() {
        return proxy;
    }
}
