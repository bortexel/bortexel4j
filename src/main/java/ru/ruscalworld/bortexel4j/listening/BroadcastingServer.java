package ru.ruscalworld.bortexel4j.listening;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.ruscalworld.bortexel4j.listening.events.EventListener;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class BroadcastingServer {
    private String url;
    private String token;
    private String name;
    private String environment;
    private Timestamp lastMessageReceived;
    private WebSocket webSocket;
    private OkHttpClient client;
    private StatusChecker statusChecker;
    private final List<EventListener> listeners = new ArrayList<>();
    private final IncomingMessageHandler incomingMessageHandler;

    public BroadcastingServer(OkHttpClient client) {
        this.client = client;
        this.url = "wss://pigeon.bortexel.net/v1/websocket";
        this.incomingMessageHandler = new IncomingMessageHandler(this);
    }

    public Logger getLogger() {
        return LoggerFactory.getLogger(this.getClass());
    }

    public void connect() {
        Request request = new Request.Builder().url(this.getURL()).build();
        this.setWebSocket(this.getClient().newWebSocket(request, new Listener(this)));

        this.setStatusChecker(new StatusChecker(this));
        this.getStatusChecker().start();
    }

    public void disconnect() {
        boolean closed = this.getWebSocket().close(1001, null);
        if (closed) {
            this.setWebSocket(null);
            this.getStatusChecker().interrupt();
            this.setStatusChecker(null);
        }
        this.getWebSocket().cancel();
    }

    public void registerListener(EventListener listener) {
        this.listeners.add(listener);
    }

    public List<EventListener> getListeners() {
        return this.listeners;
    }

    public String getURL() {
        return url;
    }

    public void setURL(String url) {
        this.url = url;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public OkHttpClient getClient() {
        return client;
    }

    public void setClient(OkHttpClient client) {
        this.client = client;
    }

    public IncomingMessageHandler getIncomingMessageHandler() {
        return incomingMessageHandler;
    }

    public Timestamp getLastMessageReceived() {
        return lastMessageReceived;
    }

    public void setLastMessageReceived(Timestamp lastMessageReceived) {
        this.lastMessageReceived = lastMessageReceived;
    }

    public WebSocket getWebSocket() {
        return webSocket;
    }

    public void setWebSocket(WebSocket webSocket) {
        this.webSocket = webSocket;
    }

    public StatusChecker getStatusChecker() {
        return statusChecker;
    }

    public void setStatusChecker(StatusChecker statusChecker) {
        this.statusChecker = statusChecker;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }
}
