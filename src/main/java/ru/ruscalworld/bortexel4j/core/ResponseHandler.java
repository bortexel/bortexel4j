package ru.ruscalworld.bortexel4j.core;

import java.io.IOException;
import java.lang.reflect.Type;

public interface ResponseHandler<T> {
    Response<T> handle(Type type, okhttp3.Response apiResponse) throws RuntimeException, IOException;
}
