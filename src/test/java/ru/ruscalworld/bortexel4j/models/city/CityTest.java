package ru.ruscalworld.bortexel4j.models.city;

import org.junit.jupiter.api.Test;
import ru.ruscalworld.bortexel4j.models.shop.Shop;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CityTest {
    @Test
    void getByID() {
        City city = City.getByID(1).execute();
        assertNotNull(city);
        assertEquals(1, city.getID());
    }

    @Test
    void getAll() {
        List<City> cities = City.getAll().execute();
        assertNotNull(cities);
        assertTrue(cities.size() > 0);
    }
}