package com.example.lab5_ultimate.controller.command;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.util.Map;

@AllArgsConstructor
public class LoadChatCommand implements Command {
    Map<String, String> authMap;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException {
        request.setAttribute("senderId", authMap.get("user"));
        request.setAttribute("role", authMap.get("role"));
        RequestDispatcher dispatcher = request.getRequestDispatcher("chat/chat.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            throw new ServletException("Failed page forwarding", e);
        }
    }
}
