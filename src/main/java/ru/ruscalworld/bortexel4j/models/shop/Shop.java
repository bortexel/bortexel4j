package ru.ruscalworld.bortexel4j.models.shop;

import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import ru.ruscalworld.bortexel4j.Bortexel4J;
import ru.ruscalworld.bortexel4j.core.Action;
import ru.ruscalworld.bortexel4j.models.economy.Report;
import ru.ruscalworld.bortexel4j.models.taxes.Taxes;
import ru.ruscalworld.bortexel4j.util.Location;

import java.util.List;

public class Shop {
    private final int id;
    private String name;
    private String description;
    private String items;

    @SerializedName("owner_id")
    private final int ownerID;

    private final Position position;
    private final Location location;
    private final Taxes tax;
    private final Images images;

    @SerializedName("city_id")
    private final int cityID;

    private final boolean verified;
    private final boolean active;

    public Shop(int id, String name, String description, String items, int ownerID, Position position, Location locaiton, Taxes tax, Images images, int cityID, boolean verified, boolean active) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.items = items;
        this.ownerID = ownerID;
        this.position = position;
        this.location = locaiton;
        this.tax = tax;
        this.images = images;
        this.cityID = cityID;
        this.verified = verified;
        this.active = active;
    }

    public static Action<Shop> getByID(int id) {
        return getByID(id, new Bortexel4J());
    }

    public static Action<Shop> getByID(int id, Bortexel4J client) {
        Action<Shop> action = new Action<>("/shops/" + id, client);
        action.setResponseType(Shop.class);
        return action;
    }

    public static Action<List<Shop>> getAll() {
        return getAll(new Bortexel4J());
    }

    public static Action<List<Shop>> getAll(Bortexel4J client) {
        Action<List<Shop>> action = new Action<>("/shops", client);
        action.setResponseType(TypeToken.getParameterized(List.class, Shop.class).getType());
        return action;
    }

    public Action<ShopReports> getReports(boolean actualOnly) {
        return getReports(actualOnly, new Bortexel4J());
    }

    public Action<ShopReports> getReports(boolean actualOnly, Bortexel4J client) {
        Action<ShopReports> action = new Action<>("/shops/" + this.getID() + "/reports", client);
        if (actualOnly) action.addQueryParam("actual", "1");
        action.setResponseType(ShopReports.class);
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public int getOwnerID() {
        return ownerID;
    }

    public Position getPosition() {
        return position;
    }

    public Location getLocation() {
        return location;
    }

    public Taxes getTax() {
        return tax;
    }

    public Images getImages() {
        return images;
    }

    public int getCityID() {
        return cityID;
    }

    public boolean isActive() {
        return active;
    }

    public boolean isVerified() {
        return verified;
    }

    public static class Images {
        @SerializedName("screenshot_url")
        private final String sreenshotURL;

        public Images(String sreenshotURL) {
            this.sreenshotURL = sreenshotURL;
        }

        public String getSreenshotURL() {
            return sreenshotURL;
        }
    }

    public static class Position {
        @SerializedName("object_name")
        private final String objectName;
        private final int location;

        public Position(String objectName, int location) {
            this.objectName = objectName;
            this.location = location;
        }

        public String getObjectName() {
            return objectName;
        }

        public int getLocation() {
            return location;
        }
    }

    public static class ShopReports {
        private final Shop shop;
        private final List<Report> reports;

        public ShopReports(Shop shop, List<Report> reports) {
            this.shop = shop;
            this.reports = reports;
        }

        public Shop getShop() {
            return shop;
        }

        public List<Report> getReports() {
            return reports;
        }
    }
}
