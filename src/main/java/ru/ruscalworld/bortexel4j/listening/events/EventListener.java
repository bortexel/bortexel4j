package ru.ruscalworld.bortexel4j.listening.events;

import ru.ruscalworld.bortexel4j.listening.events.ban.GenericBanEvent;

public class EventListener {
    public void onBanCreated(GenericBanEvent event) {}
    public void onBanUpdated(GenericBanEvent event) {}
    public void onBanDeleted(GenericBanEvent event) {}
}
