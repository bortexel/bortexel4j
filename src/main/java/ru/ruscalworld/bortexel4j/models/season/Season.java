package ru.ruscalworld.bortexel4j.models.season;

import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import ru.ruscalworld.bortexel4j.Bortexel4J;
import ru.ruscalworld.bortexel4j.core.Action;
import ru.ruscalworld.bortexel4j.models.photo.Photo;

import java.sql.Timestamp;
import java.util.List;

public class Season {
    private final int id;
    private String name;

    @SerializedName(value = "start_date")
    private Timestamp startDate;

    @SerializedName(value = "end_date")
    private Timestamp endDate;

    @SerializedName(value = "start_version")
    private String startVersion;

    @SerializedName(value = "end_version")
    private String endVersion;

    private String description;

    @SerializedName(value = "world_url")
    private String worldURL;

    @SerializedName(value = "stats_url")
    private String statsURL;

    public Season(int id, String name, Timestamp startDate, Timestamp endDate, String startVersion, String endVersion, String description, String worldURL, String statsURL) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startVersion = startVersion;
        this.endVersion = endVersion;
        this.description = description;
        this.worldURL = worldURL;
        this.statsURL = statsURL;
    }

    public static Action<Season> getByID(int id) {
        return getByID(id, new Bortexel4J());
    }

    public static Action<Season> getByID(int id, Bortexel4J client) {
        Action<Season> action = new Action<>("/seasons/" + id, client);
        action.setResponseType(Season.class);
        return action;
    }

    public static Action<List<Season>> getAll() {
        return getAll(new Bortexel4J());
    }

    public static Action<List<Season>> getAll(Bortexel4J client) {
        Action<List<Season>> action = new Action<>("/seasons", client);
        action.setResponseType(TypeToken.getParameterized(List.class, Season.class).getType());
        return action;
    }

    public Action<SeasonPhotos> getPhotos() {
        return getPhotos(new Bortexel4J());
    }

    public Action<SeasonPhotos> getPhotos(Bortexel4J client) {
        Action<SeasonPhotos> action = new Action<>("/seasons/" + this.id + "/photos", client);
        action.setResponseType(SeasonPhotos.class);
        return action;
    }

    public static class SeasonPhotos {
        private final Season season;
        private final List<Photo> photos;

        public SeasonPhotos(Season season, List<Photo> photos) {
            this.season = season;
            this.photos = photos;
        }

        public Season getSeason() {
            return season;
        }

        public List<Photo> getPhotos() {
            return photos;
        }
    }

    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public String getStartVersion() {
        return startVersion;
    }

    public void setStartVersion(String startVersion) {
        this.startVersion = startVersion;
    }

    public String getEndVersion() {
        return endVersion;
    }

    public void setEndVersion(String endVersion) {
        this.endVersion = endVersion;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWorldURL() {
        return worldURL;
    }

    public void setWorldURL(String worldURL) {
        this.worldURL = worldURL;
    }

    public String getStatsURL() {
        return statsURL;
    }

    public void setStatsURL(String statsURL) {
        this.statsURL = statsURL;
    }
}
