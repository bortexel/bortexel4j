package ru.ruscalworld.bortexel4j.models.profile;

import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.Nullable;
import ru.ruscalworld.bortexel4j.Bortexel4J;
import ru.ruscalworld.bortexel4j.core.Action;

import java.sql.Timestamp;
import java.util.List;

public class Ban {
    private final int id;
    @SerializedName("user_name")
    private final String userName;
    @SerializedName("admin_name")
    private final String adminName;
    private final String reason;
    private final boolean suspended;
    @SerializedName("by_ip")
    private final boolean byIP;
    @SerializedName("by_name")
    private final boolean byName;
    @SerializedName("created_at")
    private final Timestamp createdAt;
    @SerializedName("expires_at")
    private final Timestamp expiresAt;

    public Ban(int id, String userName, String adminName, String reason, boolean suspended, boolean byIP, boolean byName, Timestamp createdAt, Timestamp expiresAt) {
        this.id = id;
        this.userName = userName;
        this.adminName = adminName;
        this.reason = reason;
        this.suspended = suspended;
        this.byIP = byIP;
        this.byName = byName;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
    }

    public static Action<ProfileBans> getProfileBans(String userName) {
        return getProfileBans(userName, Bortexel4J.anonymous());
    }

    public static Action<ProfileBans> getProfileBans(String userName, Bortexel4J client) {
        Action<ProfileBans> action = new Action<>("/profiles/" + userName + "/bans", client);
        action.setResponseType(ProfileBans.class);
        return action;
    }

    public static class ProfileBans {
        private final Profile profile;
        private final List<Ban> bans;

        public ProfileBans(Profile profile, List<Ban> bans) {
            this.profile = profile;
            this.bans = bans;
        }

        public Profile getProfile() {
            return profile;
        }

        public List<Ban> getBans() {
            return bans;
        }
    }

    public int getID() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getAdminName() {
        return adminName;
    }

    public String getReason() {
        return reason;
    }

    public boolean isSuspended() {
        return suspended;
    }

    public boolean isByIP() {
        return byIP;
    }

    public boolean isByName() {
        return byName;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public @Nullable Timestamp getExpiresAt() {
        return expiresAt;
    }
}
