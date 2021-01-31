package ru.ruscalworld.bortexel4j.util;

import java.util.Objects;

public class APIUtil {
    public static void checkResponse(okhttp3.Response apiResponse) throws RuntimeException {
        if (apiResponse.header("Content-Type") == null) throw new RuntimeException("API server has returned invalid Content-Type header");
        if (!Objects.requireNonNull(apiResponse.header("Content-Type")).equalsIgnoreCase("application/json"))
            throw new RuntimeException("API server has returned invalid Content-Type header");
        if (apiResponse.body() == null) throw new RuntimeException("Body of API response is empty");
    }
}
