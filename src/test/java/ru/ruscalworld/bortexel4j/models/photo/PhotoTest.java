package ru.ruscalworld.bortexel4j.models.photo;

import org.junit.jupiter.api.Test;
import ru.ruscalworld.bortexel4j.Bortexel4J;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PhotoTest {
    private static final Bortexel4J client = Bortexel4J.login(System.getenv("BORTEXEL_TOKEN"), false);

    @Test
    void getByID() {
        Photo photo = Photo.getByID(2, client).execute();
        assertNotNull(photo);
        assertEquals(2, photo.getId());
    }

    @Test
    void getAll() {
        List<Photo> photos = Photo.getAll(client).execute();
        assertNotEquals(0, photos.size());
    }
}