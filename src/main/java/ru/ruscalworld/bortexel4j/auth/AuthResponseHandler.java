package ru.ruscalworld.bortexel4j.auth;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import ru.ruscalworld.bortexel4j.core.APIError;
import ru.ruscalworld.bortexel4j.core.Response;
import ru.ruscalworld.bortexel4j.core.ResponseHandler;
import ru.ruscalworld.bortexel4j.exceptions.AuthorizationException;
import ru.ruscalworld.bortexel4j.exceptions.BortexelException;
import ru.ruscalworld.bortexel4j.exceptions.LoginException;
import ru.ruscalworld.bortexel4j.exceptions.NotFoundException;
import ru.ruscalworld.bortexel4j.util.APIUtil;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AuthResponseHandler<T> implements ResponseHandler<T> {
    @Override
    public Response<T> handle(Type type, okhttp3.Response apiResponse) throws RuntimeException, IOException {
        Gson gson = new Gson();
        if (apiResponse.code() == 200 && apiResponse.body() != null) {
            APIUtil.checkResponse(apiResponse);
            T response = gson.fromJson(apiResponse.body().string(), type);
            if (response == null) return null;
            return new Response<>(apiResponse.code(), Collections.emptyList(), Collections.emptyList(), response);
        } else if (apiResponse.code() != 204) {
            assert apiResponse.body() != null;
            AuthError error = gson.fromJson(apiResponse.body().string(), AuthError.class);
            if (error.getCode() == 401) throw new LoginException(null, error.getText(), error.getCode());
            return new Response<>(apiResponse.code(), new ArrayList<APIError>() {{ add(error.getAPIError()); }}, Collections.emptyList(), null);
        } else return new Response<>(apiResponse.code(), Collections.emptyList(), Collections.emptyList(), null);
    }
}
