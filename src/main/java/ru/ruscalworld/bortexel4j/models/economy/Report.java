package ru.ruscalworld.bortexel4j.models.economy;

import com.google.gson.annotations.SerializedName;
import ru.ruscalworld.bortexel4j.Bortexel4J;
import ru.ruscalworld.bortexel4j.core.Action;
import ru.ruscalworld.bortexel4j.core.HTTPMethod;
import ru.ruscalworld.bortexel4j.util.Location;

import java.sql.Timestamp;

public class Report {
    private final int id;

    @SerializedName("stage_id")
    private final int stageID;

    @SerializedName("item_id")
    private final String itemID;

    @SerializedName("author_id")
    private final int authorID;

    private final double price;
    private final int quantity;
    private final Location location;

    @SerializedName("shop_id")
    private final int shopID;

    @SerializedName("created_at")
    private final Timestamp createdAt;

    public Report(int id, int stageID, String itemID, int authorID, double price, int quantity, Location location, int shopID, Timestamp createdAt) {
        this.id = id;
        this.stageID = stageID;
        this.itemID = itemID;
        this.authorID = authorID;
        this.price = price;
        this.quantity = quantity;
        this.location = location;
        this.shopID = shopID;
        this.createdAt = createdAt;
    }

    public static Action<Report> getByID(int id) {
        return getByID(id, new Bortexel4J());
    }

    public static Action<Report> getByID(int id, Bortexel4J client) {
        Action<Report> action = new Action<>("/economy/reports/" + id, client);
        action.setResponseType(Report.class);
        return action;
    }

    public Action<Report> create(Bortexel4J client) {
        Action<Report> action = new Action<>("/economy/reports/new", client);
        action.setResponseType(Report.class);
        action.setMethod(HTTPMethod.POST);
        action.setBody(this);
        return action;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public int getShopID() {
        return shopID;
    }

    public Location getLocation() {
        return location;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public int getAuthorID() {
        return authorID;
    }

    public String getItemID() {
        return itemID;
    }

    public int getStageID() {
        return stageID;
    }

    public int getID() {
        return id;
    }
}
