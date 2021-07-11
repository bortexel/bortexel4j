package ru.ruscalworld.bortexel4j.listening.events.city;

import ru.ruscalworld.bortexel4j.listening.events.Event;
import ru.ruscalworld.bortexel4j.models.city.City;

public class GenericCityEvent extends Event<GenericCityEvent> {
    private City city;

    public GenericCityEvent(int eventID, GenericCityEvent payload) {
        super(eventID, payload);
    }

    public GenericCityEvent(Event<Object> event) {
        super(event.getEventID(), (GenericCityEvent) event.getPayload());
        this.city = ((GenericCityEvent) event.getPayload()).getCity();
    }

    public City getCity() {
        return city;
    }

    protected void setCity(City city) {
        this.city = city;
    }
}
