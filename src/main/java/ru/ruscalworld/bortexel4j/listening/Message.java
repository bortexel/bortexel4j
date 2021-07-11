package ru.ruscalworld.bortexel4j.listening;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import okhttp3.WebSocket;
import org.jetbrains.annotations.NotNull;
import ru.ruscalworld.bortexel4j.listening.events.Event;
import ru.ruscalworld.bortexel4j.listening.events.ban.BanDeletedEvent;
import ru.ruscalworld.bortexel4j.listening.events.ban.GenericBanEvent;
import ru.ruscalworld.bortexel4j.listening.events.city.GenericCityEvent;
import ru.ruscalworld.bortexel4j.listening.events.shop.GenericShopEvent;
import ru.ruscalworld.bortexel4j.listening.events.warning.GenericWarningEvent;

import java.lang.reflect.Type;

public class Message {
    private final int operation;
    private final Object payload;

    public Message(int operation, Object payload) {
        this.operation = operation;
        this.payload = payload;
    }

    public void send(@NotNull WebSocket webSocket) {
        Gson gson = new Gson();
        String json = gson.toJson(this);
        webSocket.send(json);
    }

    public Type getEventType() {
        JsonElement payload = new Gson().toJsonTree(this.getPayload());
        switch (payload.getAsJsonObject().get("event_id").getAsInt()) {
            case Event.BAN_CREATED_EVENT:
            case Event.BAN_UPDATED_EVENT:
                return GenericBanEvent.class;
            case Event.BAN_DELETED_EVENT:
                return BanDeletedEvent.class;
            case Event.WARNING_CREATED_EVENT:
            case Event.WARNING_UPDATED_EVENT:
            case Event.WARNING_DELETED_EVENT:
                return GenericWarningEvent.class;
            case Event.SHOP_CREATED_EVENT:
            case Event.SHOP_UPDATED_EVENT:
            case Event.SHOP_DELETED_EVENT:
                return GenericShopEvent.class;
            case Event.CITY_CREATED_EVENT:
            case Event.CITY_UPDATED_EVENT:
            case Event.CITY_DELETED_EVENT:
                return GenericCityEvent.class;
        }

        return Object.class;
    }

    public int getOperation() {
        return operation;
    }

    public Object getPayload() {
        return payload;
    }

    public static class Authorization {
        private final String token;
        private String name;
        private String environment;

        public Authorization(String token) {
            this.token = token;
        }

        public String getToken() {
            return token;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEnvironment() {
            return environment;
        }

        public void setEnvironment(String environment) {
            this.environment = environment;
        }
    }
}
