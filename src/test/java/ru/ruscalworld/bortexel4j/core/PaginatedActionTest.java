package ru.ruscalworld.bortexel4j.core;

import org.junit.jupiter.api.Test;
import ru.ruscalworld.bortexel4j.Bortexel4J;
import ru.ruscalworld.bortexel4j.models.account.Account;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PaginatedActionTest {
    @Test
    void getNext() {
        PaginatedListAction<Account> action = Account.getAll(Bortexel4J.login());
        List<Account> accounts = action.execute();
        assertNotNull(accounts);
        assertTrue(accounts.size() > 0);

        accounts = action.getNext().execute();
        assertNotNull(accounts);
        assertTrue(accounts.size() > 0);
        assertTrue(accounts.get(0).getID() > 10);
    }
}