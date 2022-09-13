package com.example.lab5_ultimate.controller.command;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.util.Map;

@AllArgsConstructor
public class LoadSearchCommand implements Command {
    Map<String, String> authMap;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        request.setAttribute("role", authMap == null ? "guest" : authMap.getOrDefault("role", "guest"));
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("search/search.jsp");
        try {
            requestDispatcher.forward(request, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
