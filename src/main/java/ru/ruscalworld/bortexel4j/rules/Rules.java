package ru.ruscalworld.bortexel4j.rules;

import com.google.gson.annotations.SerializedName;
import ru.ruscalworld.bortexel4j.RuleClient;
import ru.ruscalworld.bortexel4j.core.Action;

import java.sql.Timestamp;
import java.util.List;

public class Rules {
    private final String name;

    @SerializedName("last_update")
    private final Timestamp lastUpdate;

    private final List<RulePart> parts;

    public Rules(String name, Timestamp lastUpdate, List<RulePart> parts) {
        this.name = name;
        this.lastUpdate = lastUpdate;
        this.parts = parts;
    }

    public static Action<Rules> getByName(String name) {
        return getByName(name, new RuleClient());
    }

    public static Action<Rules> getByName(String name, RuleClient client) {
        Action<Rules> action = new Action<>("/" + name + ".json", client);
        action.setResponseHandler(new RuleResponseHandler<>());
        action.setResponseType(Rules.class);
        return action;
    }

    public String getName() {
        return name;
    }

    public Timestamp getLastUpdateTime() {
        return lastUpdate;
    }

    public List<RulePart> getParts() {
        return parts;
    }
}
