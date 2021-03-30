package ru.ruscalworld.bortexel4j.models.warning;

import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import ru.ruscalworld.bortexel4j.Bortexel4J;
import ru.ruscalworld.bortexel4j.core.Action;

import java.sql.Timestamp;
import java.util.List;

public class Warning {
    private final int id;
    private final String username;

    @SerializedName("account_id")
    private final int accountID;

    private final String admin;

    @SerializedName("admin_id")
    private final int adminID;

    private int power;
    private String reason;

    @SerializedName("created_at")
    private final Timestamp createdAt;

    public Warning(int id, String username, int accountID, String admin, int adminID, int power, String reason, Timestamp createdAt) {
        this.id = id;
        this.username = username;
        this.accountID = accountID;
        this.admin = admin;
        this.adminID = adminID;
        this.power = power;
        this.reason = reason;
        this.createdAt = createdAt;
    }

    public static Action<List<Warning>> getAll(Bortexel4J client) {
        Action<List<Warning>> action = new Action<>("/warnings", client);
        action.setResponseType(TypeToken.getParameterized(List.class, Warning.class).getType());
        return action;
    }

    public static Action<Warning> getByID(int id, Bortexel4J client) {
        Action<Warning> action = new Action<>("/warnings/" + id, client);
        action.setResponseType(Warning.class);
        return action;
    }

    public int getID() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public int getAccountID() {
        return accountID;
    }

    public String getAdmin() {
        return admin;
    }

    public int getAdminID() {
        return adminID;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }
}
