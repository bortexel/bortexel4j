package ru.ruscalworld.bortexel4j.listening.events.ban;

import com.google.gson.annotations.SerializedName;
import ru.ruscalworld.bortexel4j.listening.events.Event;
import ru.ruscalworld.bortexel4j.listening.events.warning.GenericWarningEvent;
import ru.ruscalworld.bortexel4j.models.account.Account;

public class BanDeletedEvent extends GenericBanEvent {
    @SerializedName("deleted_by")
    private Account deletedBy;
    private String reason;

    public BanDeletedEvent(Event<Object> event) {
        super(event);
        BanDeletedEvent deletedEvent = (BanDeletedEvent) event.getPayload();
        this.deletedBy = deletedEvent.getDeletedBy();
        this.reason = deletedEvent.getReason();
    }

    public BanDeletedEvent(int eventID, BanDeletedEvent payload) {
        super(eventID, payload);
        this.deletedBy = payload.getDeletedBy();
        this.reason = payload.getReason();
    }

    public Account getDeletedBy() {
        return deletedBy;
    }

    protected void setDeletedBy(Account deletedBy) {
        this.deletedBy = deletedBy;
    }

    public String getReason() {
        return reason;
    }

    protected void setReason(String reason) {
        this.reason = reason;
    }
}
