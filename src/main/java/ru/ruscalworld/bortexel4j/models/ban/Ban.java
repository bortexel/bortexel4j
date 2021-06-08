package ru.ruscalworld.bortexel4j.models.ban;

import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import org.jetbrains.annotations.Nullable;
import ru.ruscalworld.bortexel4j.Bortexel4J;
import ru.ruscalworld.bortexel4j.core.Action;
import ru.ruscalworld.bortexel4j.core.HTTPMethod;
import ru.ruscalworld.bortexel4j.core.PaginatedListAction;

import java.net.InetAddress;
import java.sql.Timestamp;
import java.util.List;

public class Ban {
    private final int id;
    private final String username;
    private String reason;

    @SerializedName("account_id")
    private final int accountID;

    @Nullable
    @SerializedName("admin_name")
    private final String adminName;

    @SerializedName("admin_id")
    private final int adminID;

    private String ip;

    @SerializedName(value = "by_name")
    private boolean byName;

    @SerializedName(value = "by_ip")
    private boolean byIP;

    private boolean suspended;

    @SerializedName("created_at")
    private final Timestamp createdAt;

    @SerializedName("expires_at")
    private Timestamp expiresAt;

    public Ban(int id, String username, String reason, int accountID, @Nullable String bannedBy, int admin, Timestamp time, Timestamp expire, String ip, boolean byName, boolean byIP, boolean paused) {
        this.id = id;
        this.username = username;
        this.reason = reason;
        this.accountID = accountID;
        this.adminName = bannedBy;
        this.adminID = admin;
        this.createdAt = time;
        this.expiresAt = expire;
        this.ip = ip;
        this.byName = byName;
        this.byIP = byIP;
        this.suspended = paused;
    }

    public static Action<Ban> getByID(int id, Bortexel4J client) {
        Action<Ban> action = new Action<>("/bans/" + id, client);
        action.setResponseType(Ban.class);
        return action;
    }

    public static PaginatedListAction<Ban> getAll(Bortexel4J client) {
        PaginatedListAction<Ban> action = new PaginatedListAction<>("/bans", client);
        action.setResponseListType(Ban.class);
        return action;
    }

    public static Action<List<Ban>> getByAddress(InetAddress address, Bortexel4J client) {
        return getByAddress(address.getHostAddress(), client);
    }

    public static Action<List<Ban>> getByAddress(String address, Bortexel4J client) {
        Action<List<Ban>> action = new Action<>("/addresses/" + address + "/bans", client);
        action.setResponseListType(Ban.class);
        return action;
    }

    public Action<Ban> create(Bortexel4J client) {
        Action<Ban> action = new Action<>("/bans/new", client);
        action.setResponseType(Ban.class);
        action.setMethod(HTTPMethod.POST);
        action.setBody(this);
        action.setExecutorID(this.getAdminID());
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

    public @Nullable String getAdminName() {
        return adminName;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getExpiresAt() {
        return expiresAt;
    }

    public void setExpireTime(Timestamp expire) {
        this.expiresAt = expire;
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

    public boolean isSuspended() {
        return suspended;
    }

    public void setSuspended(boolean suspended) {
        this.suspended = suspended;
    }

    public int getAdminID() {
        return adminID;
    }

    public boolean isPermanent() {
        return this.getExpiresAt() == null || this.getExpiresAt().after(new Timestamp(1893456000000L));
    }

    public boolean isActual() {
        return (this.getExpiresAt() == null || this.getExpiresAt().after(new Timestamp(System.currentTimeMillis()))) && !this.isSuspended();
    }

    public int getAccountID() {
        return accountID;
    }

    public static class Builder {
        private int accountID;
        private String reason;
        private int adminID;
        private String ip;
        private boolean byIP;
        private boolean byName;
        private Timestamp expiresAt;

        public Ban build() {
            return new Ban(0, null, this.getReason(), this.getAccountID(), null, this.getAdminID(), null, this.getExpiresAt(), this.getIP(), this.isByName(), this.isByIP(), false);
        }

        public int getAccountID() {
            return accountID;
        }

        public Builder setAccountID(int accountID) {
            this.accountID = accountID;
            return this;
        }

        public Timestamp getExpiresAt() {
            return this.expiresAt;
        }

        public Builder setExpiresAt(Timestamp expiresAt) {
            this.expiresAt = expiresAt;
            return this;
        }

        public String getReason() {
            return reason;
        }

        public Builder setReason(String reason) {
            this.reason = reason;
            return this;
        }

        public int getAdminID() {
            return adminID;
        }

        public Builder setAdminID(int adminID) {
            this.adminID = adminID;
            return this;
        }

        public String getIP() {
            return ip;
        }

        public Builder setIP(String ip) {
            this.ip = ip;
            return this;
        }

        public boolean isByIP() {
            return byIP;
        }

        public Builder setByIP(boolean byIP) {
            this.byIP = byIP;
            return this;
        }

        public boolean isByName() {
            return byName;
        }

        public Builder setByName(boolean byName) {
            this.byName = byName;
            return this;
        }
    }
}
