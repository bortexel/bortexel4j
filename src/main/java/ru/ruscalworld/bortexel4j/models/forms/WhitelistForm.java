package ru.ruscalworld.bortexel4j.models.forms;

import com.google.gson.annotations.SerializedName;
import ru.ruscalworld.bortexel4j.Bortexel4J;
import ru.ruscalworld.bortexel4j.core.Action;
import ru.ruscalworld.bortexel4j.core.PaginatedListAction;
import ru.ruscalworld.bortexel4j.models.user.Address;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class WhitelistForm {
    private final int id;
    @SerializedName("account_id")
    private final int accountID;
    private final String username;
    @SerializedName("minecraft_id")
    private final UUID minecraftID;
    private final Human human;
    private final List<Question> questions = new ArrayList<>();
    @SerializedName("friend_name")
    private final String friendName;
    private final String email;
    private final LinkedEly ely;
    @SerializedName("is_accepted")
    private final boolean isAccepted;
    private final int conclusion;
    @SerializedName("admin_name")
    private final String adminName;
    @SerializedName("admin_id")
    private final Integer adminID;
    private final Address address;
    @SerializedName("created_at")
    private final Timestamp createdAt;
    @SerializedName("completed_at")
    private final Timestamp completedAt;
    @SerializedName("reviewed_at")
    private final Timestamp reviewedAt;

    public WhitelistForm(int id, int accountID, String username, UUID minecraftID, Human human, String friendName, String email, LinkedEly ely, boolean isAccepted, int conclusion, String adminName, Integer adminID, Address address, Timestamp createdAt, Timestamp completedAt, Timestamp reviewedAt) {
        this.id = id;
        this.accountID = accountID;
        this.username = username;
        this.minecraftID = minecraftID;
        this.human = human;
        this.friendName = friendName;
        this.email = email;
        this.ely = ely;
        this.isAccepted = isAccepted;
        this.conclusion = conclusion;
        this.adminName = adminName;
        this.adminID = adminID;
        this.address = address;
        this.createdAt = createdAt;
        this.completedAt = completedAt;
        this.reviewedAt = reviewedAt;
    }

    public static Action<WhitelistForm> getByID(int id, Bortexel4J client) {
        Action<WhitelistForm> action = new Action<>("/forms/whitelist/" + id, client);
        action.setResponseType(WhitelistForm.class);
        return action;
    }

    public static PaginatedListAction<WhitelistForm> getAll(Bortexel4J client) {
        PaginatedListAction<WhitelistForm> action = new PaginatedListAction<>("/forms/whitelist", client);
        action.setResponseListType(WhitelistForm.class);
        return action;
    }

    public int getID() {
        return id;
    }

    public int getAccountID() {
        return accountID;
    }

    public String getUsername() {
        return username;
    }

    public UUID getMinecraftID() {
        return minecraftID;
    }

    public Human getHuman() {
        return human;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public String getFriendName() {
        return friendName;
    }

    public String getEmail() {
        return email;
    }

    public LinkedEly getEly() {
        return ely;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public int getConclusion() {
        return conclusion;
    }

    public String getAdminName() {
        return adminName;
    }

    public Integer getAdminID() {
        return adminID;
    }

    public Address getAddress() {
        return address;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getCompletedAt() {
        return completedAt;
    }

    public Timestamp getReviewedAt() {
        return reviewedAt;
    }

    public static class LinkedEly {
        private final String id;
        private final String email;
        @SerializedName("registration_time")
        private final Timestamp registrationTime;

        public LinkedEly(String id, String email, Timestamp registrationTime) {
            this.id = id;
            this.email = email;
            this.registrationTime = registrationTime;
        }

        public String getID() {
            return id;
        }

        public String getEmail() {
            return email;
        }

        public Timestamp getRegistrationTime() {
            return registrationTime;
        }
    }
}
