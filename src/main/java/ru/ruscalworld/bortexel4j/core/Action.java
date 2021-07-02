package ru.ruscalworld.bortexel4j.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import okhttp3.Response;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.slf4j.LoggerFactory;
import ru.ruscalworld.bortexel4j.Client;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

public class Action<T> {
    private final String endpoint;
    private final Client client;
    private Type type;
    private HTTPMethod method;
    private Object body;
    private int executorID;
    private String realAddress;
    private final boolean handleResult;
    private ru.ruscalworld.bortexel4j.core.Response<T> lastResponse;
    private final HashMap<String, String> queryParams = new HashMap<>();
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
            Response response = this.getClient().createCall(this.makeRequest()).execute();
            if (this.shouldHandleResult()) {
                ru.ruscalworld.bortexel4j.core.Response<T> bResponse = getResponseHandler().handle(this.getType(), response);
                if (bResponse == null) return null;

                this.setLastResponse(bResponse);
                return bResponse.getResponse();
            } else {
                if (response.body() != null) response.body().close();
                return null;
            }
        } catch (IOException e) {
            LoggerFactory.getLogger(this.getClass()).trace("Exception while executing action: ", e);
        }

        return null;
    }

    public void executeAsync() {
        executeAsync(response -> {});
    }

    public void executeAsync(Consumer<T> success) {
        executeAsync(success, error -> {});
    }

    public void executeAsync(Consumer<T> success, Consumer<Exception> error) {
        this.getClient().createCall(this.makeRequest()).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                error.accept(e);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) {
                if (shouldHandleResult()) {
                    try {
                        ru.ruscalworld.bortexel4j.core.Response<T> bResponse = getResponseHandler().handle(getType(), response);
                        if (bResponse == null) return;

                        success.accept(bResponse.getResponse());
                        setLastResponse(bResponse);
                    } catch (Exception e) {
                        error.accept(e);
                    }
                } else if (response.body() != null) response.body().close();
            }
        });
    }

    private Request makeRequest() {
        Request.Builder builder = this.getClient().getDefaultRequestBuilder();
        builder.url(this.getClient().getApiUrl() + this.getEndpoint() + this.buildQueryParams());

        if (this.getMethod() != HTTPMethod.GET) {
            RequestBody body = RequestBody.create(MediaType.parse("application/json"), this.getBody());
            builder.post(body);
            builder.method(this.getMethod().toString(), body);
            builder.header("Content-Type", "application/json");
        }

        if (this.getExecutorID() != 0) builder.header("X-Proxied-Account-ID", "" + this.getExecutorID());
        if (this.getRealAddress() != null) {
            builder.header("X-Forwarded-For", this.getRealAddress());
            builder.header("X-Real-IP", this.getRealAddress());
        }

        return builder.build();
    }

    public void setQueryParam(String param, String value) {
        this.getQueryParams().put(param, value);
    }

    protected String buildQueryParams() {
        if (this.getQueryParams().size() == 0) return "";

        List<String> params = new ArrayList<>();
        this.getQueryParams().forEach((param, value) -> params.add(param + "=" + value));
        return "?" + String.join("&", params);
    }

    public Client getClient() {
        return client;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public Type getType() {
        return type;
    }

    public void setResponseType(Type type) {
        this.type = type;
    }

    public void setResponseListType(Type type) {
        this.setResponseType(TypeToken.getParameterized(List.class, type).getType());
    }

    public HTTPMethod getMethod() {
        return method;
    }

    public void setMethod(HTTPMethod method) {
        this.method = method;
    }

    public String getBody() {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").create();
        return gson.toJson(this.body);
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public boolean shouldHandleResult() {
        return handleResult;
    }

    public ResponseHandler<T> getResponseHandler() {
        return responseHandler;
    }

    public void setResponseHandler(ResponseHandler<T> responseHandler) {
        this.responseHandler = responseHandler;
    }

    public int getExecutorID() {
        return executorID;
    }

    public void setExecutorID(int executorID) {
        this.executorID = executorID;
    }

    public HashMap<String, String> getQueryParams() {
        return queryParams;
    }

    public ru.ruscalworld.bortexel4j.core.Response<T> getLastResponse() {
        return lastResponse;
    }

    public void setLastResponse(ru.ruscalworld.bortexel4j.core.Response<T> lastResponse) {
        this.lastResponse = lastResponse;
    }

    public String getRealAddress() {
        return realAddress;
    }

    public void setRealAddress(String realAddress) {
        this.realAddress = realAddress;
    }
}
