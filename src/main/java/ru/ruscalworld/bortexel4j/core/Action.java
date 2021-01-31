package ru.ruscalworld.bortexel4j.core;

import com.google.gson.Gson;
import okhttp3.*;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import ru.ruscalworld.bortexel4j.Bortexel4J;
import ru.ruscalworld.bortexel4j.Client;

import java.io.IOException;
import java.lang.reflect.Type;

public class Action<T> {
    private final String endpoint;
    private final Client client;
    private Type type;
    private HTTPMethod method;
    private Object body;
    private final boolean handleResult;
    private ResponseHandler<T> responseHandler = new APIResponseHandler<>();

    public Action(String endpoint, boolean handleResult, Client client) {
        this.endpoint = endpoint;
        this.client = client;
        this.method = HTTPMethod.GET;
        this.handleResult = handleResult;
    }

    public Action(String endpoint, Client client) {
        this.endpoint = endpoint;
        this.client = client;
        this.method = HTTPMethod.GET;
        this.handleResult = true;
    }

    public T execute() {
        try {
            Response response = Bortexel4J.createCall(this.makeRequest()).execute();
            if (this.handleResult) {
                ru.ruscalworld.bortexel4j.core.Response<T> bResponse = getResponseHandler().handle(this.type, response);
                if (bResponse != null) return bResponse.getResponse();
            } else {
                if (response.body() != null) response.body().close();
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void executeAsync() {
        executeAsync(response -> {});
    }

    public void executeAsync(Callback<T> callback) {
        Bortexel4J.createCall(this.makeRequest()).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                callback.handle(null);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (handleResult) {
                    ru.ruscalworld.bortexel4j.core.Response<T> bResponse = getResponseHandler().handle(type, response);
                    if (bResponse != null) callback.handle(bResponse.getResponse());
                } else if (response.body() != null) response.body().close();
            }
        });
    }

    private Request makeRequest() {
        Request.Builder builder = this.client.getDefaultRequestBuilder();
        builder.url(this.client.getApiUrl() + endpoint);

        if (this.method != HTTPMethod.GET) {
            RequestBody body = RequestBody.create(MediaType.parse("application/json"), this.getBody());
            builder.post(body);
            builder.method(this.method.toString(), body);
            builder.header("Content-Type", "application/json");
        }

        return builder.build();
    }

    public Type getType() {
        return type;
    }

    public void setResponseType(Type type) {
        this.type = type;
    }

    public HTTPMethod getMethod() {
        return method;
    }

    public void setMethod(HTTPMethod method) {
        this.method = method;
    }

    public String getBody() {
        return new Gson().toJson(this.body);
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public boolean isHandleResult() {
        return handleResult;
    }

    public ResponseHandler<T> getResponseHandler() {
        return responseHandler;
    }

    public void setResponseHandler(ResponseHandler<T> responseHandler) {
        this.responseHandler = responseHandler;
    }
}
