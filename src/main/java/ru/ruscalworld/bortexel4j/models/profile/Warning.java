package ru.ruscalworld.bortexel4j.models.profile;

import com.google.gson.annotations.SerializedName;
import ru.ruscalworld.bortexel4j.Bortexel4J;
import ru.ruscalworld.bortexel4j.core.Action;

import java.sql.Timestamp;
import java.util.List;

public class Warning {
    private final int id;
    @SerializedName("user_name")
    private final String userName;
    @SerializedName("admin_name")
    private final String adminName;
    private final String reason;
    private final int power;
    @SerializedName("created_at")
    private final Timestamp createdAt;

    public Warning(int id, String userName, String adminName, String reason, int power, Timestamp createdAt) {
        this.id = id;
        this.userName = userName;
        this.adminName = adminName;
        this.reason = reason;
        this.power = power;
        this.createdAt = createdAt;
    }

    public static Action<ProfileWarnings> getProfileWarnings(String userName) {
        return getProfileWarnings(userName, Bortexel4J.anonymous());
    }

    public static Action<ProfileWarnings> getProfileWarnings(String userName, Bortexel4J client) {
        Action<ProfileWarnings> action = new Action<>("/profiles/" + userName + "/warnings", client);
        action.setResponseType(ProfileWarnings.class);
        return action;
    }

    public static class ProfileWarnings {
        private final Profile profile;
        private final List<Warning> warnings;

        public ProfileWarnings(Profile profile, List<Warning> warnings) {
            this.profile = profile;
            this.warnings = warnings;
        }

        public Profile getProfile() {
            return profile;
        }

        public List<Warning> getWarnings() {
            return warnings;
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

    public int getPower() {
        return power;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }
}
