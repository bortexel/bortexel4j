package ru.ruscalworld.bortexel4j.rules;

import com.google.gson.Gson;
import ru.ruscalworld.bortexel4j.auth.AuthError;
import ru.ruscalworld.bortexel4j.core.APIError;
import ru.ruscalworld.bortexel4j.core.Response;
import ru.ruscalworld.bortexel4j.core.ResponseHandler;
import ru.ruscalworld.bortexel4j.exceptions.LoginException;
import ru.ruscalworld.bortexel4j.util.APIUtil;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;

public class RuleResponseHandler<T> implements ResponseHandler<T> {
    @Override
    public Response<T> handle(Type type, okhttp3.Response apiResponse) throws RuntimeException, IOException {
        Gson gson = new Gson();
        if (apiResponse.code() == 200 && apiResponse.body() != null) {
            APIUtil.checkResponse(apiResponse);
            T response = gson.fromJson(apiResponse.body().string(), type);
            if (response == null) return null;
            return new Response<>(apiResponse.code(), Collections.emptyList(), Collections.emptyList(), response);
        } else return new Response<>(apiResponse.code(), Collections.emptyList(), Collections.emptyList(), null);
    }
}
