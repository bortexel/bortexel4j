package ru.ruscalworld.bortexel4j.listening.events;

import com.google.gson.annotations.SerializedName;

import java.math.BigInteger;

public class Event<T> {
    public static final int CREATED_OFFSET = 0;
    public static final int UPDATED_OFFSET = 1;
    public static final int DELETED_OFFSET = 2;

    public static final int WARNING_CREATED_EVENT = 20 + CREATED_OFFSET;
    public static final int WARNING_UPDATED_EVENT = 20 + UPDATED_OFFSET;
    public static final int WARNING_DELETED_EVENT = 20 + DELETED_OFFSET;

    public static final int BAN_CREATED_EVENT = 30 + CREATED_OFFSET;
    public static final int BAN_UPDATED_EVENT = 30 + UPDATED_OFFSET;
    public static final int BAN_DELETED_EVENT = 30 + DELETED_OFFSET;

    public static final int SHOP_CREATED_EVENT = 40 + CREATED_OFFSET;
    public static final int SHOP_UPDATED_EVENT = 40 + UPDATED_OFFSET;
    public static final int SHOP_DELETED_EVENT = 40 + DELETED_OFFSET;

    public static final int CITY_CREATED_EVENT = 50 + CREATED_OFFSET;
    public static final int CITY_UPDATED_EVENT = 50 + UPDATED_OFFSET;
    public static final int CITY_DELETED_EVENT = 50 + DELETED_OFFSET;

    public static final int USER_CREATED_EVENT = 60 + CREATED_OFFSET;
    public static final int USER_UPDATED_EVENT = 60 + UPDATED_OFFSET;
    public static final int USER_DELETED_EVENT = 60 + DELETED_OFFSET;
    public static final int USER_ACTIVITY_UPDATED_EVENT = 60 + 3;

    public static final int FORM_ACTION_BASE = 1000;
    public static final int WHITELIST_FORM_OFFSET = 10;

    public static final int ACCOUNT_LINK_BASE = 3000;
    public static final int DISCORD_LINK_OFFSET = 10;

    @SerializedName("event_id")
    private final int eventID;
    private final T payload;

    public Event(int eventID, T payload) {
        this.eventID = eventID;
        this.payload = payload;
    }

    public int getEventID() {
        return eventID;
    }

    public T getPayload() {
        return payload;
    }
}
