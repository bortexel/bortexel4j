package ru.ruscalworld.bortexel4j.models;

import com.google.gson.reflect.TypeToken;
import ru.ruscalworld.bortexel4j.Bortexel4J;
import ru.ruscalworld.bortexel4j.core.Action;

import java.lang.reflect.Type;
import java.util.List;

@Deprecated
public class Model<T> {
    private final String endpoint;
    private final Type type;

    protected Model(String endpoint, Type type) {
        this.endpoint = endpoint;
        this.type = type;
    }

    public Action<List<T>> getAll(int page, Bortexel4J client) {
        Action<List<T>> action = new Action<>(endpoint + "?page=" + page, client);
        action.setType(TypeToken.getParameterized(List.class, this.type).getType());
        return action;
    }

    public Action<T> getById(int id, Bortexel4J client) {
        return getBySomething("id", id, client);
    }

    public Action<T> getBySomething(String param, Object value, Bortexel4J client) {
        Action<T> action = new Action<>(endpoint.replace("{" + param + "}", value.toString()), client);
        action.setType(this.type);
        return action;
    }
}
