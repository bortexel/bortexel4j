package ru.ruscalworld.bortexel4j.models.photo;

import org.junit.jupiter.api.Test;
import ru.ruscalworld.bortexel4j.Bortexel4J;

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

    @Test
    void getProxy() {
        Photo photo = Photo.getByID(2, true, Bortexel4J.anonymous()).execute();
        assertNotNull(photo);
        assertNotNull(photo.getProxy());
        assertNotNull(photo.getProxy().getOriginalURL());
    }
}