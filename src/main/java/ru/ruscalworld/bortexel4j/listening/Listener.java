package ru.ruscalworld.bortexel4j.listening;

import com.google.gson.Gson;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import org.jetbrains.annotations.NotNull;

public class Listener extends WebSocketListener {
    private final BroadcastingServer server;

    public Listener(BroadcastingServer server) {
        this.server = server;
    }

    @Override
    public void onOpen(@NotNull WebSocket webSocket, @NotNull Response response) {
        System.out.println("Connected to WebSocket at " + server.getURL());
        new Message(Operations.HELLO, new Message.Authorization(this.server.getToken())).send(webSocket);
    }

    @Override
    public void onMessage(@NotNull WebSocket webSocket, @NotNull String text) {
        Gson gson = new Gson();
        Message message = gson.fromJson(text, Message.class);
        boolean success = this.server.getIncomingMessageHandler().handleMessage(message, gson);
        if (!success) webSocket.close(1001, null);
    }

    @Override
    public void onClosed(@NotNull WebSocket webSocket, int code, @NotNull String reason) {
        if (code != 1000 && code != 1001) {
            System.out.println("WebSocket was closed with error code " + code + " (" + reason + "), reconnecting...");
            server.connect();
        }
    }

    @Override
    public void onFailure(@NotNull WebSocket webSocket, @NotNull Throwable throwable, Response response) {
        throwable.printStackTrace();
    }
}
