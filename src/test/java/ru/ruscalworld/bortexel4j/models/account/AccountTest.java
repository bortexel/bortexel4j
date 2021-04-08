package ru.ruscalworld.bortexel4j.models.account;

import org.junit.jupiter.api.Test;
import ru.ruscalworld.bortexel4j.Bortexel4J;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {
    private static final Bortexel4J client = Bortexel4J.login();

    @Test
    void getByID() {
        Account account = Account.getByID(1, client).execute();
        assertNotNull(account);
        assertEquals(1, account.getID());
    }

    @Test
    void getByDiscordID() {
        Account account = Account.getByDiscordID("496297262952218638", client).execute();
        assertNotNull(account);
        assertEquals(1, account.getID());
        assertEquals("496297262952218638", account.getDiscordID());
    }

    @Test
    void getAll() {
        List<Account> accounts = Account.getAll(client).execute();
        assertNotNull(accounts);
        assertNotEquals(0, accounts.size());
        assertEquals(1, accounts.get(0).getID());
    }
}