package ru.ruscalworld.bortexel4j.listening.events.ban;

import ru.ruscalworld.bortexel4j.listening.events.Event;
import ru.ruscalworld.bortexel4j.models.ban.Ban;

public class GenericBanEvent extends Event<Ban> {
    public GenericBanEvent(Event<Object> event) {
        super(event.getEventID(), (Ban) event.getPayload());
    }

    public GenericBanEvent(int eventID, Ban payload) {
        super(eventID, payload);
    }
}
