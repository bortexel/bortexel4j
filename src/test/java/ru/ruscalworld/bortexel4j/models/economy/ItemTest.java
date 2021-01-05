package ru.ruscalworld.bortexel4j.models.economy;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {
    @Test
    void getByID() {
        Item item = Item.getByID("diamond_axe").execute();
        assertNotNull(item);
        assertEquals("diamond_axe", item.getId());
    }

    @Test
    void getPrices() {
        Item item = Item.getByID("diamond_axe").execute();
        assertNotNull(item);

        Item.ItemPrices prices = item.getPrices().execute();
        assertNotNull(prices);
        assertNotEquals(0, prices.getPrices().size());
    }
}