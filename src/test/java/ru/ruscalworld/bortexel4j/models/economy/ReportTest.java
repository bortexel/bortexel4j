package ru.ruscalworld.bortexel4j.models.economy;

import org.junit.jupiter.api.Test;
import ru.ruscalworld.bortexel4j.Bortexel4J;
import ru.ruscalworld.bortexel4j.util.Location;

import static org.junit.jupiter.api.Assertions.*;

class ReportTest {
    @Test
    void getByID() {
        Report report = Report.getByID(1).execute();
        assertNotNull(report);
        assertEquals(1, report.getID());
    }

    @Test
    void builder() {
        Report.Builder builder = new Report.Builder();
        builder.setQuantity(Quantity.NORMAL);
        builder.setItemID("test");
        builder.setShopID(1);
        builder.setLocation(new Location(1, 2, 3, "world"));
        builder.setPrice(10);

        Report report = builder.build();
        assertEquals(Quantity.NORMAL, report.getQuantity());
        assertEquals("test", report.getItemID());
        assertEquals(1, report.getShopID());
        assertEquals("world", report.getLocation().getWorld());
        assertEquals(10, report.getPrice());
    }
}