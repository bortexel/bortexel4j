package ru.ruscalworld.bortexel4j.core;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import okhttp3.ResponseBody;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.ruscalworld.bortexel4j.exceptions.AuthorizationException;
import ru.ruscalworld.bortexel4j.exceptions.BortexelException;
import ru.ruscalworld.bortexel4j.exceptions.NotFoundException;
import ru.ruscalworld.bortexel4j.util.APIUtil;

import java.io.IOException;
import java.lang.reflect.Type;

public class APIResponseHandler<T> implements ResponseHandler<T> {
    public Response<T> handle(@NotNull Type type, okhttp3.Response apiResponse) throws RuntimeException, IOException {
        APIUtil.checkResponse(apiResponse);

        Gson gson = new Gson();
        ResponseBody body = apiResponse.body();
        assert body != null;
        Type fullType = TypeToken.getParameterized(Response.class, type).getType();
        Response<T> response = gson.fromJson(body.string(), fullType);

        if (response == null) return null;

        Logger logger = LoggerFactory.getLogger(this.getClass());
        for (String warning : response.getWarnings()) logger.warn("Bortexel API warning: {}", warning);
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
