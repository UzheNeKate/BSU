package com.example.lab5_ultimate.controller;

import jakarta.websocket.OnMessage;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@ServerEndpoint("/chat")
public class ChatEndpoint  {

    private HashMap<String, List<String>> buffer = new HashMap<>();

    @OnMessage
    public void onMessage(Session session, String msg) {
        if (!session.getUserProperties().containsKey("sender")) {
            String[] someInfo = msg.split(":=:");
            session.getUserProperties().put("sender", someInfo[1]);
            session.getUserProperties().put("role", someInfo[2]);
        } else {
            String sender = (String) session.getUserProperties().get("sender");
            String role = (String) session.getUserProperties().get("role");
            String receiverName = "";
            String receiverRole = "";
            String message;
            switch (role) {
                case "admin":
                    String[] someInfo = msg.split(":=:");
                    receiverName = someInfo[0];
                    message = someInfo[1];
                    break;
                default:
                    receiverRole = "admin";
                    message = msg;
            }

            for (Session sessionReceiver : session.getOpenSessions()) {
                if (sessionReceiver.isOpen()) {
                    if (sessionReceiver.getUserProperties().containsKey("sender")) {
                        String sessionSenderName = (String) sessionReceiver.getUserProperties().get("sender");
                        String sessionSenderRole = (String) sessionReceiver.getUserProperties().get("role");
                        if (receiverRole.equals(sessionSenderRole) || receiverName.equals(sessionSenderName)) {
                            try {
                                sessionReceiver.getBasicRemote().sendText(sender + ": " + message);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }

    }
}
