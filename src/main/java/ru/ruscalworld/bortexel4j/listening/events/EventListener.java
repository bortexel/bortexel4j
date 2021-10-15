package ru.ruscalworld.bortexel4j.listening.events;

import ru.ruscalworld.bortexel4j.listening.events.account.GenericDiscordLinkEvent;
import ru.ruscalworld.bortexel4j.listening.events.ban.BanDeletedEvent;
import ru.ruscalworld.bortexel4j.listening.events.ban.GenericBanEvent;
import ru.ruscalworld.bortexel4j.listening.events.city.GenericCityEvent;
import ru.ruscalworld.bortexel4j.listening.events.forms.GenericRequestEvent;
import ru.ruscalworld.bortexel4j.listening.events.shop.GenericShopEvent;
import ru.ruscalworld.bortexel4j.listening.events.user.GenericUserEvent;
import ru.ruscalworld.bortexel4j.listening.events.warning.GenericWarningEvent;

public class EventListener {
    public void onBanCreated(GenericBanEvent event) {}
    public void onBanUpdated(GenericBanEvent event) {}
    public void onBanDeleted(BanDeletedEvent event) {}

    public void onWarningCreated(GenericWarningEvent event) {}
    public void onWarningUpdated(GenericWarningEvent event) {}
    public void onWarningDeleted(GenericWarningEvent event) {}

    public void onShopCreated(GenericShopEvent event) {}
    public void onShopUpdated(GenericShopEvent event) {}
    public void onShopDeleted(GenericShopEvent event) {}

    public void onCityCreated(GenericCityEvent event) {}
    public void onCityUpdated(GenericCityEvent event) {}
    public void onCityDeleted(GenericCityEvent event) {}

    public void onUserActivityUpdated(GenericUserEvent event) {}

    public void onWhitelistFormSubmitted(GenericRequestEvent event) {}
    public void onWhitelistFormReviewed(GenericRequestEvent event) {}

    public void onAccountDiscordLinked(GenericDiscordLinkEvent event) {}
    public void onAccountDiscordUnlinked(GenericDiscordLinkEvent event) {}
}
