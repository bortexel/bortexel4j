package ru.ruscalworld.bortexel4j.models.ban;

import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import ru.ruscalworld.bortexel4j.Bortexel4J;
import ru.ruscalworld.bortexel4j.core.Action;

import java.net.InetAddress;
import java.sql.Timestamp;
import java.util.List;

public class Ban {
    private final int id;
    private final String username;
    private final int user;
    private String reason;

    @SerializedName(value = "banned_by")
    private final String bannedBy;
    private final int admin;

    private final Timestamp time;
    private Timestamp expire;
    private String ip;

    @SerializedName(value = "by_name")
    private boolean byName;

    @SerializedName(value = "by_ip")
    private boolean byIP;

    private boolean paused;

    public Ban(int id, String username, int user, String reason, String bannedBy, int admin, Timestamp time, Timestamp expire, String ip, boolean byName, boolean byIP, boolean paused) {
        this.id = id;
        this.username = username;
        this.user = user;
        this.reason = reason;
        this.bannedBy = bannedBy;
        this.admin = admin;
        this.time = time;
        this.expire = expire;
        this.ip = ip;
        this.byName = byName;
        this.byIP = byIP;
        this.paused = paused;
    }

    public static Action<Ban> getByID(int id, Bortexel4J client) {
        Action<Ban> action = new Action<>("/bans/" + id, client);
        action.setResponseType(Ban.class);
        return action;
    }

    public static Action<List<Ban>> getAll(Bortexel4J client) {
        Action<List<Ban>> action = new Action<>("/bans", client);
        action.setResponseType(TypeToken.getParameterized(List.class, Ban.class).getType());
        return action;
    }

    public static Action<List<Ban>> getByAddress(InetAddress address, Bortexel4J client) {
        return getByAddress(address.getHostAddress(), client);
    }

    public static Action<List<Ban>> getByAddress(String address, Bortexel4J client) {
        Action<List<Ban>> action = new Action<>("/addresses/" + address + "/bans", client);
        action.setResponseType(TypeToken.getParameterized(List.class, Ban.class).getType());
        return action;
    }

    public int getID() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getBannedBy() {
        return bannedBy;
    }

    public Timestamp getTime() {
        return time;
    }

    public Timestamp getExpireTime() {
        return expire;
    }

    public void setExpireTime(Timestamp expire) {
        this.expire = expire;
    }

    public String getIP() {
        return ip;
    }

    public void setIP(String ip) {
        this.ip = ip;
    }

    public boolean isBannedByName() {
        return byName;
    }

    public void setBannedByName(boolean byName) {
        this.byName = byName;
    }

    public boolean isBannedByIP() {
        return byIP;
    }

    public void setBannedByIP(boolean byIP) {
        this.byIP = byIP;
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public int getAdminID() {
        return admin;
    }

    public int getUserID() {
        return user;
    }

    public boolean isPermanent() {
        return this.getExpireTime().after(new Timestamp(1893456000000L));
    }

    public boolean isActual() {
        return this.getExpireTime().after(new Timestamp(System.currentTimeMillis())) && !this.isPaused();
    }
}
