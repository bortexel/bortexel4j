package ru.ruscalworld.bortexel4j.listening.events;

import ru.ruscalworld.bortexel4j.listening.events.ban.BanDeletedEvent;
import ru.ruscalworld.bortexel4j.listening.events.ban.GenericBanEvent;
import ru.ruscalworld.bortexel4j.listening.events.warning.GenericWarningEvent;

public class EventListener {
    public void onBanCreated(GenericBanEvent event) {}
    public void onBanUpdated(GenericBanEvent event) {}
    public void onBanDeleted(BanDeletedEvent event) {}

    public void onWarningCreated(GenericWarningEvent event) {}
    public void onWarningUpdated(GenericWarningEvent event) {}
    public void onWarningDeleted(GenericWarningEvent event) {}
}
