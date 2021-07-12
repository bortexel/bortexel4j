package ru.ruscalworld.bortexel4j.listening.events.user;

import ru.ruscalworld.bortexel4j.listening.events.Event;
import ru.ruscalworld.bortexel4j.models.user.User;

public class GenericUserEvent extends Event<GenericUserEvent> {
    private User user;

    public GenericUserEvent(int eventID, GenericUserEvent payload) {
        super(eventID, payload);
    }

    public GenericUserEvent(Event<Object> event) {
        super(event.getEventID(), (GenericUserEvent) event.getPayload());
        this.user = ((GenericUserEvent) event.getPayload()).getUser();
    }

    public User getUser() {
        return user;
    }

    protected void setUser(User user) {
        this.user = user;
    }
}
