package com.example.lab5_ultimate.controller.command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.IOException;

@NoArgsConstructor @AllArgsConstructor
public class LoadLoginCommand implements Command {
    boolean failedAttempt;
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("log-in/log-in.jsp");
        request.setAttribute("failedAttempt", failedAttempt);
        try {
            requestDispatcher.forward(request, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
