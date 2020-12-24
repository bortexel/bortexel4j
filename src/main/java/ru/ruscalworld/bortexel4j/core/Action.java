package ru.ruscalworld.bortexel4j.core;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import ru.ruscalworld.bortexel4j.Bortexel4J;

import java.io.IOException;
import java.lang.reflect.Type;

public class Action<T> {
    private final String endpoint;
    private final Bortexel4J client;
    private Type type;

    public Action(String endpoint, Bortexel4J client) {
        this.endpoint = endpoint;
        this.client = client;
    }

    public T execute() {
        try {
            Response response = Bortexel4J.createCall(this.makeRequest()).execute();
            if (response.body() == null) throw new RuntimeException("Body of API response is empty");
            ru.ruscalworld.bortexel4j.core.Response<T> bResponse = new ResponseHandler<T>().handle(this.type, response.body().string());
            if (bResponse != null) return bResponse.getResponse();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void executeAsync(Callback<T> callback) {
        Bortexel4J.createCall(this.makeRequest()).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                callback.handle(null);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response.body() == null) throw new RuntimeException("Body of API response is empty");
                ru.ruscalworld.bortexel4j.core.Response<T> bResponse = new ResponseHandler<T>().handle(type, response.body().string());
                if (bResponse != null) callback.handle(bResponse.getResponse());
            }
        });
    }

    private Request makeRequest() {
        Request.Builder builder = this.client.getDefaultRequestBuilder();
        builder.url(Bortexel4J.API_URL + endpoint);
        return builder.build();
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
