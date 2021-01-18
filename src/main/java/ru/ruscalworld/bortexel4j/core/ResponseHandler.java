package ru.ruscalworld.bortexel4j.core;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ru.ruscalworld.bortexel4j.exceptions.AuthorizationException;
import ru.ruscalworld.bortexel4j.exceptions.BortexelException;
import ru.ruscalworld.bortexel4j.exceptions.NotFoundException;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Objects;

public class ResponseHandler<T> {
    public Response<T> handle(Type type, okhttp3.Response apiResponse) throws RuntimeException, IOException {
        if (apiResponse.header("Content-Type") == null) throw new RuntimeException("API server has returned invalid Conent-Type header");
        if (!Objects.requireNonNull(apiResponse.header("Content-Type")).equalsIgnoreCase("application/json"))
            throw new RuntimeException("API server has returned invalid Conent-Type header");
        if (apiResponse.body() == null) throw new RuntimeException("Body of API response is empty");

        Gson gson = new Gson();
        Type fullType = TypeToken.getParameterized(Response.class, type).getType();
        Response<T> response = gson.fromJson(apiResponse.body().string(), fullType);

        if (response == null) return null;

        for (String warning : response.getWarnings()) System.out.println("Bortexel API warning: " + warning);
        for (APIError error : response.getErrors()) {
            switch (response.getStatus()) {
                case 401:
                    throw new AuthorizationException(error, response.getStatus());
                case 404:
                    throw new NotFoundException(error, response.getStatus());
                default:
                    throw new BortexelException(error, response.getStatus());
            }
        }

        return response;
    }
}
