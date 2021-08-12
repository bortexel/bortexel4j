package ru.ruscalworld.bortexel4j.listening;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import org.slf4j.LoggerFactory;
import ru.ruscalworld.bortexel4j.exceptions.WebSocketException;
import ru.ruscalworld.bortexel4j.listening.events.Event;
import ru.ruscalworld.bortexel4j.listening.events.EventListener;
import ru.ruscalworld.bortexel4j.listening.events.ban.BanDeletedEvent;
import ru.ruscalworld.bortexel4j.listening.events.ban.GenericBanEvent;
import ru.ruscalworld.bortexel4j.listening.events.city.GenericCityEvent;
import ru.ruscalworld.bortexel4j.listening.events.forms.GenericRequestEvent;
import ru.ruscalworld.bortexel4j.listening.events.shop.GenericShopEvent;
import ru.ruscalworld.bortexel4j.listening.events.user.GenericUserEvent;
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
                this.server.getLogger().trace("Exception in WebSocket connection: ", exception);
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
                    break;
                case Event.BAN_UPDATED_EVENT:
                    listener.onBanUpdated(new GenericBanEvent(event));
                    break;
                case Event.BAN_DELETED_EVENT:
                    listener.onBanDeleted(new BanDeletedEvent(event));
                    break;
                case Event.WARNING_CREATED_EVENT:
                    listener.onWarningCreated(new GenericWarningEvent(event));
                    break;
                case Event.WARNING_UPDATED_EVENT:
                    listener.onWarningUpdated(new GenericWarningEvent(event));
                    break;
                case Event.WARNING_DELETED_EVENT:
                    listener.onWarningDeleted(new GenericWarningEvent(event));
                    break;
                case Event.SHOP_CREATED_EVENT:
                    listener.onShopCreated(new GenericShopEvent(event));
                    break;
                case Event.SHOP_UPDATED_EVENT:
                    listener.onShopUpdated(new GenericShopEvent(event));
                    break;
                case Event.SHOP_DELETED_EVENT:
                    listener.onShopDeleted(new GenericShopEvent(event));
                    break;
                case Event.CITY_CREATED_EVENT:
                    listener.onCityCreated(new GenericCityEvent(event));
                    break;
                case Event.CITY_UPDATED_EVENT:
                    listener.onCityUpdated(new GenericCityEvent(event));
                    break;
                case Event.CITY_DELETED_EVENT:
                    listener.onCityDeleted(new GenericCityEvent(event));
                    break;
                case Event.USER_ACTIVITY_UPDATED_EVENT:
                    listener.onUserActivityUpdated(new GenericUserEvent(event));
                    break;
                case Event.FORM_ACTION_BASE + Event.WHITELIST_FORM_OFFSET + 3:
                    listener.onWhitelistFormSubmitted(new GenericRequestEvent(event));
                    break;
                case Event.FORM_ACTION_BASE + Event.WHITELIST_FORM_OFFSET + 4:
                    listener.onWhitelistFormReviewed(new GenericRequestEvent(event));
                    break;
            }
        }
    }

    public BroadcastingServer getServer() {
        return server;
    }
}
