package ru.ruscalworld.bortexel4j.models.city;

import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.Nullable;
import ru.ruscalworld.bortexel4j.Bortexel4J;
import ru.ruscalworld.bortexel4j.core.Action;
import ru.ruscalworld.bortexel4j.core.PaginatedListAction;
import ru.ruscalworld.bortexel4j.models.taxes.Taxes;
import ru.ruscalworld.bortexel4j.util.Location;

import java.sql.Timestamp;

public class City {
    private final int id;
    private String name;
    private String style;
    private String description;
    @SerializedName("owner_id")
    private int ownerID;
    @SerializedName("founded_at")
    private final Timestamp foundedAt;
    private final Location location;
    private final Points points;
    private final Images images;
    private final Taxes tax;
    private final int season;
    private boolean active;

    public City(int id, String name, String style, String description, int ownerID, Timestamp foundedAt, Location location, Points points, Images images, Taxes tax, int season, boolean active) {
        this.id = id;
        this.name = name;
        this.style = style;
        this.description = description;
        this.ownerID = ownerID;
        this.foundedAt = foundedAt;
        this.location = location;
        this.points = points;
        this.images = images;
        this.tax = tax;
        this.season = season;
        this.active = active;
    }

    public static Action<City> getByID(int id) {
        return getByID(id, Bortexel4J.anonymous());
    }

    public static Action<City> getByID(int id, Bortexel4J client) {
        Action<City> action = new Action<>("/cities/" + id, client);
        action.setResponseType(City.class);
        return action;
    }

    public static PaginatedListAction<City> getAll() {
        return getAll(Bortexel4J.anonymous());
    }

    public static PaginatedListAction<City> getAll(Bortexel4J client) {
        PaginatedListAction<City> action = new PaginatedListAction<>("/cities", client);
        action.setResponseListType(City.class);
        return action;
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

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }

    public Timestamp getFoundedAt() {
        return foundedAt;
    }

    public Location getLocation() {
        return location;
    }

    public Points getPoints() {
        return points;
    }

    public Images getImages() {
        return images;
    }

    public Taxes getTax() {
        return tax;
    }

    public int getSeason() {
        return season;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public static class Images {
        @SerializedName("screenshot_url")
        private final @Nullable String screenshotURL;
        @SerializedName("emblem_url")
        private final @Nullable String emblemURL;

        public Images(@Nullable String screenshotURL, @Nullable String emblemURL) {
            this.screenshotURL = screenshotURL;
            this.emblemURL = emblemURL;
        }

        public @Nullable String getScreenshotURL() {
            return screenshotURL;
        }

        public @Nullable String getEmblemURL() {
            return emblemURL;
        }
    }

    public static class Points {
        private final String overworld;
        private final String nether;

        public Points(String overworld, String nether) {
            this.overworld = overworld;
            this.nether = nether;
        }

        public String getOverworld() {
            return overworld;
        }

        public String getNether() {
            return nether;
        }
    }
}
