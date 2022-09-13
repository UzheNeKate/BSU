package com.example.lab5_ultimate.controller.command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.util.Map;

@AllArgsConstructor
public class LoadNumbersCommand implements Command {
    Map<String, String> authMap;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        request.setAttribute("role", authMap == null ? "guest" : authMap.getOrDefault("role", "guest"));
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("number-items/number-items.jsp");
        try {
            requestDispatcher.forward(request, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
