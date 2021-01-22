package ru.ruscalworld.bortexel4j.models.season;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SeasonTest {

    @Test
    void getAll() {
        List<Season> seasons = Season.getAll().execute();
        assertNotNull(seasons);
        assertNotEquals(0, seasons.size());
    }

    @Test
    void getPhotos() {
        Season season = Season.getByID(1).execute();
        assertNotNull(season);
        assertEquals(1, season.getID());

        Season.SeasonPhotos photos = season.getPhotos().execute();
        assertNotNull(photos);
        assertNotNull(photos.getSeason());
        assertEquals(1, photos.getSeason().getID());
        assertNotNull(photos.getPhotos());
        assertNotEquals(0, photos.getPhotos().size());
    }
}