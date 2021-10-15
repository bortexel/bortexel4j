package ru.ruscalworld.bortexel4j.listening.events.account;

import ru.ruscalworld.bortexel4j.listening.events.Event;
import ru.ruscalworld.bortexel4j.models.account.Account;

public class GenericAccountEvent extends Event<GenericAccountEvent> {
    private Account account;

    public GenericAccountEvent(Event<Object> event) {
        super(event.getEventID(), (GenericAccountEvent) event.getPayload());
        this.account = ((GenericAccountEvent) event.getPayload()).getAccount();
    }

    public GenericAccountEvent(int eventID, GenericAccountEvent payload) {
        super(eventID, payload);
        this.account = payload.getAccount();
    }

    public Account getAccount() {
        return account;
    }

    protected void setAccount(Account account) {
        this.account = account;
    }
}
