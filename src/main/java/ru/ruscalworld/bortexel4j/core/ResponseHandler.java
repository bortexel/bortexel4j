package ru.ruscalworld.bortexel4j.core;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ru.ruscalworld.bortexel4j.exceptions.AuthorizationException;
import ru.ruscalworld.bortexel4j.exceptions.BortexelException;
import ru.ruscalworld.bortexel4j.exceptions.NotFoundException;

import java.lang.reflect.Type;

public class ResponseHandler<T> {
    public Response<T> handle(Type type, String rawResponse) throws RuntimeException {
        Gson gson = new Gson();
        Type fullType = TypeToken.getParameterized(Response.class, type).getType();
        Response<T> response = gson.fromJson(rawResponse, fullType);

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
