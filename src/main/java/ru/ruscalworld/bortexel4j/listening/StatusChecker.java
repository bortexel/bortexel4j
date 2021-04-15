package ru.ruscalworld.bortexel4j.listening;

import okhttp3.WebSocket;

import java.sql.Timestamp;

public class StatusChecker extends Thread {
    public static final long PING_INTERVAL = 30000L;
    private final BroadcastingServer server;

    public StatusChecker(BroadcastingServer server) {
        this.server = server;

        this.setName("BCS Connection Checker");
        this.setDaemon(true);
    }

    @Override
    public void run() {
        while (this.getServer().getWebSocket() != null) {
            try {
                Thread.sleep(PING_INTERVAL);
            } catch (InterruptedException ignored) { }

            // Send ping request
            WebSocket webSocket = this.getServer().getWebSocket();
            new Message(Operations.PING, null).send(webSocket);

            //     DEADLINE        NOW
            // - - (x - 60) - - - - x - - - >
            //  ^              ^
            //  |              Normal
            //  Check fail

            Timestamp deadline = new Timestamp(System.currentTimeMillis() - PING_INTERVAL * 2);
            if (this.getServer().getLastMessageReceived() == null || deadline.after(this.getServer().getLastMessageReceived())) {
                long interval = System.currentTimeMillis() - this.getServer().getLastMessageReceived().getTime();
                System.out.println("[BCS] Connection seems to be broken, reconnecting. Last message received " + interval + "ms ago.");

                this.getServer().disconnect();
                this.getServer().connect();
            }
        }
    }

    public BroadcastingServer getServer() {
        return server;
    }
}
