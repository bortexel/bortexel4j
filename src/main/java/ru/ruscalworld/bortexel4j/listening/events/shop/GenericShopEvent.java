package ru.ruscalworld.bortexel4j.listening.events.shop;

import ru.ruscalworld.bortexel4j.listening.events.Event;
import ru.ruscalworld.bortexel4j.models.city.City;

public class GenericShopEvent extends Event<GenericShopEvent> {
    private City shop;

    public GenericShopEvent(int eventID, GenericShopEvent payload) {
        super(eventID, payload);
    }

    public GenericShopEvent(Event<Object> event) {
        super(event.getEventID(), (GenericShopEvent) event.getPayload());
        this.shop = ((GenericShopEvent) event.getPayload()).getShop();
    }

    public City getShop() {
        return shop;
    }

    protected void setShop(City shop) {
        this.shop = shop;
    }
}
