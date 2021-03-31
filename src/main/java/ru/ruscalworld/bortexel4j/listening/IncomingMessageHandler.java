package ru.ruscalworld.bortexel4j.listening;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import ru.ruscalworld.bortexel4j.exceptions.WebSocketException;
import ru.ruscalworld.bortexel4j.listening.events.Event;
import ru.ruscalworld.bortexel4j.listening.events.EventListener;
import ru.ruscalworld.bortexel4j.listening.events.ban.GenericBanEvent;
import ru.ruscalworld.bortexel4j.listening.events.warning.GenericWarningEvent;

import java.sql.Timestamp;

public class IncomingMessageHandler {
    private final BroadcastingServer server;

    public IncomingMessageHandler(BroadcastingServer server) {
        this.server = server;
    }

    public boolean handleMessage(Message message, Gson gson) {
        this.server.setLastMessageReceived(new Timestamp(System.currentTimeMillis()));
        JsonElement payload = gson.toJsonTree(message.getPayload());

        switch (message.getOperation()) {
            case Operations.ERROR:
                WebSocketException exception = gson.fromJson(payload, WebSocketException.class);
                exception.printStackTrace();
                return false;
            case Operations.NEW_EVENT:
                Event<Object> event = gson.fromJson(payload, TypeToken.getParameterized(Event.class, message.getEventType()).getType());
                this.broadcastEvent(event);
                return true;
        }

        return true;
    }

    public void broadcastEvent(Event<Object> event) {
        for (EventListener listener : this.getServer().getListeners()) {
            switch (event.getEventID()) {
                case Event.BAN_CREATED_EVENT:
                    listener.onBanCreated(new GenericBanEvent(event));
                case Event.BAN_UPDATED_EVENT:
                    listener.onBanUpdated(new GenericBanEvent(event));
                case Event.BAN_DELETED_EVENT:
                    listener.onBanDeleted(new GenericBanEvent(event));
                case Event.WARNING_CREATED_EVENT:
                    listener.onWarningCreated(new GenericWarningEvent(event));
                case Event.WARNING_UPDATED_EVENT:
                    listener.onWarningUpdated(new GenericWarningEvent(event));
                case Event.WARNING_DELETED_EVENT:
                    listener.onWarningDeleted(new GenericWarningEvent(event));
            }
        }
    }

    public BroadcastingServer getServer() {
        return server;
    }
}
