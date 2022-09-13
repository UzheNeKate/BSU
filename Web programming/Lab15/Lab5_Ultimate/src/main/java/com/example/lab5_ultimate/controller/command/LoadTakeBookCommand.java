package com.example.lab5_ultimate.controller.command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.util.Map;

@AllArgsConstructor
public class LoadTakeBookCommand implements Command {
    Map<String, String> authMap;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        if(authMap == null) {
            new LoadLoginCommand().execute(request, response);
            return;
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("take-book/take-book.jsp");
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
