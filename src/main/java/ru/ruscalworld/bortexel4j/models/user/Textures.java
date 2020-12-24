package ru.ruscalworld.bortexel4j.models.user;

public class Textures {
    private final boolean valid;
    private final String signature;
    private final String value;

    public Textures(boolean valid, String signature, String value) {
        this.valid = valid;
        this.signature = signature;
        this.value = value;
    }

    public boolean isValid() {
        return valid;
    }

    public String getSignature() {
        return signature;
    }

    public String getValue() {
        return value;
    }
}
