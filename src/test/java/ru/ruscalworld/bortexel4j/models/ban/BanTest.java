package ru.ruscalworld.bortexel4j.models.ban;

import org.junit.jupiter.api.Test;
import ru.ruscalworld.bortexel4j.Bortexel4J;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BanTest {
    private static final Bortexel4J client = Bortexel4J.login();

    @Test
    void getByID() {
        Ban ban = Ban.getByID(1, client).execute();
        assertNotNull(ban);
        assertEquals(1, ban.getID());
    }

    @Test
    void getAll() {
        List<Ban> bans = Ban.getAll(client).execute();
        assertNotNull(bans);
        assertNotEquals(0, bans.size());
    }

    @Test
    void getByAddress() {
        List<Ban> bans = Ban.getByAddress("195.250.47.17", client).execute();
        assertNotNull(bans);
        assertNotEquals(0, bans.size());

        Ban ban = bans.get(0);
        assertEquals(1, ban.getID());
        assertFalse(ban.isActual());
        assertFalse(ban.isPermanent());
        assertFalse(ban.isSuspended());
    }

    @Test
    void getByInetAddress() throws UnknownHostException {
        InetAddress address = InetAddress.getByName("195.250.47.17");
        List<Ban> bans = Ban.getByAddress(address, client).execute();

        assertNotNull(bans);
        assertNotEquals(0, bans.size());
        assertEquals(1, bans.get(0).getID());
    }
}