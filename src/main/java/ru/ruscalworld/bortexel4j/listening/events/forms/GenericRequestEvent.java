package ru.ruscalworld.bortexel4j.listening.events.forms;

import ru.ruscalworld.bortexel4j.listening.events.Event;
import ru.ruscalworld.bortexel4j.models.forms.WhitelistForm;

public class GenericRequestEvent extends Event<GenericRequestEvent> {
    private WhitelistForm request;

    public GenericRequestEvent(int eventID, GenericRequestEvent payload) {
        super(eventID, payload);
    }

    public GenericRequestEvent(Event<Object> event) {
        super(event.getEventID(), (GenericRequestEvent) event.getPayload());
        this.request = ((GenericRequestEvent) event.getPayload()).getRequest();
    }

    public WhitelistForm getRequest() {
        return request;
    }

    protected void setRequest(WhitelistForm user) {
        this.request = user;
    }
}
