package ru.ruscalworld.bortexel4j.listening.events.warning;

import ru.ruscalworld.bortexel4j.listening.events.Event;
import ru.ruscalworld.bortexel4j.models.warning.Warning;

public class GenericWarningEvent extends Event<Warning> {
    public GenericWarningEvent(Event<Object> event) {
        super(event.getEventID(), (Warning) event.getPayload());
    }

    public GenericWarningEvent(int eventID, Warning payload) {
        super(eventID, payload);
    }
}
