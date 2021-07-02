package ru.ruscalworld.bortexel4j.listening.events.ban;

import ru.ruscalworld.bortexel4j.listening.events.Event;
import ru.ruscalworld.bortexel4j.models.ban.Ban;

public class GenericBanEvent extends Event<GenericBanEvent> {
    private Ban ban;

    public GenericBanEvent(Event<Object> event) {
        super(event.getEventID(), (GenericBanEvent) event.getPayload());
    }

    public GenericBanEvent(int eventID, GenericBanEvent payload) {
        super(eventID, payload);
    }

    public Ban getBan() {
        return ban;
    }

    protected void setBan(Ban ban) {
        this.ban = ban;
    }
}
