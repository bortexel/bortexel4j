package ru.ruscalworld.bortexel4j.models.profile;

import com.google.gson.annotations.SerializedName;
import org.jetbrains.annotations.Nullable;
import ru.ruscalworld.bortexel4j.Bortexel4J;
import ru.ruscalworld.bortexel4j.core.Action;

import java.sql.Timestamp;
import java.util.List;

public class Profile {
    @SerializedName("account_id")
    private final int accountID;
    @SerializedName("user_id")
    private final int userID;
    private final String username;
    @SerializedName("last_login")
    private final Timestamp lastLogin;
    private final long groups;
    private final Bans bans;
    private final Warnings warnings;
    @SerializedName("discord")
    private final String discordTag;
    @SerializedName("discord_id")
    private final String discordID;

    public Profile(int accountID, int userID, String username, Timestamp lastLogin, long groups, Bans bans, Warnings warnings, String discordTag, String discordID) {
        this.accountID = accountID;
        this.userID = userID;
        this.username = username;
        this.lastLogin = lastLogin;
        this.groups = groups;
        this.bans = bans;
        this.warnings = warnings;
        this.discordTag = discordTag;
        this.discordID = discordID;
    }

    public static Action<Profile> getByUserName(String username) {
        return getByUserName(username, Bortexel4J.anonymous());
    }

    public static Action<Profile> getByUserName(String username, Bortexel4J client) {
        Action<Profile> action = new Action<>("/profiles/" + username, client);
        action.setResponseType(Profile.class);
        return action;
    }

    public int getAccountID() {
        return accountID;
    }

    public int getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public Timestamp getLastLogin() {
        return lastLogin;
    }

    public long getGroups() {
        return groups;
    }

    public Bans getBans() {
        return bans;
    }

    public Warnings getWarnings() {
        return warnings;
    }

    public @Nullable String getDiscordTag() {
        return discordTag;
    }

    public @Nullable String getDiscordID() {
        return discordID;
    }

    public static class Warnings {
        private final int count;
        @SerializedName("total_power")
        private final int totalPower;
        @SerializedName("current_power")
        private final int currentPower;
        private final List<String> reasons;

        public Warnings(int count, int totalPower, int currentPower, List<String> reasons) {
            this.count = count;
            this.totalPower = totalPower;
            this.currentPower = currentPower;
            this.reasons = reasons;
        }

        public int getCount() {
            return count;
        }

        public int getTotalPower() {
            return totalPower;
        }

        public int getCurrentPower() {
            return currentPower;
        }

        public List<String> getReasons() {
            return reasons;
        }
    }

    public static class Bans {
        private final int count;
        @SerializedName("active_count")
        private final int activeCount;
        @SerializedName("total_duration")
        private final long totalDuration;
        @SerializedName("suspended_count")
        private final int suspendedCount;
        @SerializedName("permanent_count")
        private final int permanentCount;
        private final List<String> reasons;

        public Bans(int count, int activeCount, long totalDuration, int suspendedCount, int permanentCount, List<String> reasons) {
            this.count = count;
            this.activeCount = activeCount;
            this.totalDuration = totalDuration;
            this.suspendedCount = suspendedCount;
            this.permanentCount = permanentCount;
            this.reasons = reasons;
        }

        public int getCount() {
            return count;
        }

        public int getActiveCount() {
            return activeCount;
        }

        public long getTotalDuration() {
            return totalDuration;
        }

        public int getSuspendedCount() {
            return suspendedCount;
        }

        public int getPermanentCount() {
            return permanentCount;
        }

        public List<String> getReasons() {
            return reasons;
        }
    }
}
