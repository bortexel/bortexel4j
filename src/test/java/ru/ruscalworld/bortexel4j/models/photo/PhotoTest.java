package ru.ruscalworld.bortexel4j.models.photo;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PhotoTest {
    @Test
    void getByID() {
        Photo photo = Photo.getByID(2).execute();
        assertNotNull(photo);
        assertEquals(2, photo.getID());
    }

    @Test
    void getAll() {
        List<Photo> photos = Photo.getAll().execute();
        assertNotEquals(0, photos.size());
    }
}