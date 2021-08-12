package ru.ruscalworld.bortexel4j.models.user;

import com.google.gson.annotations.SerializedName;

import java.net.InetAddress;
import java.sql.Timestamp;

public class Address {
    private final int id;
    private final InetAddress address;
    @SerializedName("fraud_score")
    private final int fraudScore;
    @SerializedName("country_code")
    private final String countryCode;
    private final String location;
    @SerializedName("created_at")
    private final Timestamp createdAt;
    @SerializedName("updated_at")
    private final Timestamp updatedAt;

    public Address(int id, InetAddress address, int fraudScore, String countryCode, String location, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.address = address;
        this.fraudScore = fraudScore;
        this.countryCode = countryCode;
        this.location = location;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getID() {
        return id;
    }

    public InetAddress getAddress() {
        return address;
    }

    public int getFraudScore() {
        return fraudScore;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getLocation() {
        return location;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }
}
