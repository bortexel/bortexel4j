package ru.ruscalworld.bortexel4j.listening.events.warning;

import ru.ruscalworld.bortexel4j.listening.events.Event;
import ru.ruscalworld.bortexel4j.models.warning.Warning;

public class GenericWarningEvent extends Event<GenericWarningEvent> {
    private Warning warning;

    public GenericWarningEvent(Event<Object> event) {
        super(event.getEventID(), (GenericWarningEvent) event.getPayload());
        this.warning = ((GenericWarningEvent) event.getPayload()).getWarning();
    }

    public GenericWarningEvent(int eventID, GenericWarningEvent payload) {
        super(eventID, payload);
        this.warning = payload.getWarning();
    }

    public Warning getWarning() {
        return warning;
    }

    protected void setWarning(Warning warning) {
        this.warning = warning;
    }
}
