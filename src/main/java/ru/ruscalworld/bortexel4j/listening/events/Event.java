package ru.ruscalworld.bortexel4j.listening.events;

import com.google.gson.annotations.SerializedName;

import java.math.BigInteger;

public class Event<T> {
    public static final int WARNING_CREATED_EVENT = 20;
    public static final int WARNING_UPDATED_EVENT = 21;
    public static final int WARNING_DELETED_EVENT = 22;

    public static final int BAN_CREATED_EVENT = 30;
    public static final int BAN_UPDATED_EVENT = 31;
    public static final int BAN_DELETED_EVENT = 32;

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
