package ru.ruscalworld.bortexel4j.core;

import com.google.gson.Gson;
import okhttp3.*;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import ru.ruscalworld.bortexel4j.Bortexel4J;

import java.io.IOException;
import java.lang.reflect.Type;

public class Action<T> {
    private final String endpoint;
    private final Bortexel4J client;
    private Type type;
    private HTTPMethod method;
    private Object body;
    private final boolean handleResult;

    public Action(String endpoint, boolean handleResult, Bortexel4J client) {
        this.endpoint = endpoint;
        this.client = client;
        this.method = HTTPMethod.GET;
        this.handleResult = handleResult;
    }

    public Action(String endpoint, Bortexel4J client) {
        this.endpoint = endpoint;
        this.client = client;
        this.method = HTTPMethod.GET;
        this.handleResult = true;
    }

    public T execute() {
        try {
            Response response = Bortexel4J.createCall(this.makeRequest()).execute();
            ru.ruscalworld.bortexel4j.core.Response<T> bResponse = new ResponseHandler<T>().handle(this.type, response);
            if (bResponse != null) return bResponse.getResponse();
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
                if (!handleResult) return;
                ru.ruscalworld.bortexel4j.core.Response<T> bResponse = new ResponseHandler<T>().handle(type, response);
                if (bResponse != null) callback.handle(bResponse.getResponse());
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
}
