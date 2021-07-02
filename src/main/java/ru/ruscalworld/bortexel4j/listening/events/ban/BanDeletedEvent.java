package ru.ruscalworld.bortexel4j.listening.events.ban;

import com.google.gson.annotations.SerializedName;
import ru.ruscalworld.bortexel4j.listening.events.Event;
import ru.ruscalworld.bortexel4j.models.account.Account;

public class BanDeletedEvent extends GenericBanEvent {
    @SerializedName("deleted_by")
    private Account deletedBy;
    private String reason;

    public BanDeletedEvent(Event<Object> event) {
        super(event);
    }

    public BanDeletedEvent(int eventID, BanDeletedEvent payload) {
        super(eventID, payload);
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
