package ru.ruscalworld.bortexel4j.listening.events.shop;

import ru.ruscalworld.bortexel4j.listening.events.Event;
import ru.ruscalworld.bortexel4j.models.shop.Shop;

public class GenericShopEvent extends Event<GenericShopEvent> {
    private Shop shop;

    public GenericShopEvent(int eventID, GenericShopEvent payload) {
        super(eventID, payload);
    }

    public GenericShopEvent(Event<Object> event) {
        super(event.getEventID(), (GenericShopEvent) event.getPayload());
        this.shop = ((GenericShopEvent) event.getPayload()).getShop();
    }

    public Shop getShop() {
        return shop;
    }

    protected void setShop(Shop shop) {
        this.shop = shop;
    }
}
