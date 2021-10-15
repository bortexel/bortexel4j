package ru.ruscalworld.bortexel4j.listening.events.account;

import ru.ruscalworld.bortexel4j.listening.events.Event;
import ru.ruscalworld.bortexel4j.models.account.LinkedDiscord;

public class GenericDiscordLinkEvent extends GenericAccountEvent {
    private LinkedDiscord discord;

    public GenericDiscordLinkEvent(Event<Object> event) {
        super(event);
        this.discord = ((GenericDiscordLinkEvent) event.getPayload()).getDiscord();
    }

    public GenericDiscordLinkEvent(int eventID, GenericDiscordLinkEvent payload) {
        super(eventID, payload);
        this.discord = payload.getDiscord();
    }

    public LinkedDiscord getDiscord() {
        return discord;
    }

    protected void setDiscord(LinkedDiscord discord) {
        this.discord = discord;
    }
}
