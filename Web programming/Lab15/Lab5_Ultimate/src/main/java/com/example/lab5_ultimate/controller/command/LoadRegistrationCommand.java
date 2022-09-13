package com.example.lab5_ultimate.controller.command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

import java.io.IOException;

@AllArgsConstructor
public class LoadRegistrationCommand implements Command{
    String message;
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("registration/registration.jsp");
        request.setAttribute("errorMessage", message);
        try {
            requestDispatcher.forward(request, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
