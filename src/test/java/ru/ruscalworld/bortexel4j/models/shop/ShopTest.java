package ru.ruscalworld.bortexel4j.models.shop;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShopTest {
    @Test
    void getByID() {
        Shop shop = Shop.getByID(1).execute();
        assertNotNull(shop);
        assertEquals(1, shop.getID());

        Shop.ShopReports reports = shop.getReports(true).execute();
        assertNotNull(reports);
        assertTrue(reports.getReports().size() > 0);
    }

    @Test
    void getAll() {
        List<Shop> shops = Shop.getAll().execute();
        assertNotNull(shops);
        assertTrue(shops.size() > 0);
    }
}