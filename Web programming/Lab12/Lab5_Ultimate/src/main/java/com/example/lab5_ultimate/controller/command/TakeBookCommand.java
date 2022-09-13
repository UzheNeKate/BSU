package com.example.lab5_ultimate.controller.command;

import com.example.lab5_ultimate.controller.LibraryController;
import com.example.lab5_ultimate.model.exception.ControllerException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

import java.sql.Date;
import java.util.Map;

@AllArgsConstructor
public class TakeBookCommand implements Command {

    Map<String, String> authMap;
    LibraryController controller;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        if(authMap == null) {
            new LoadLoginCommand().execute(request, response);
            return;
        }

        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String reader = authMap.getOrDefault("user", "");

        if (reader.equals("")) {
            new LoadLoginCommand().execute(request, response);
            return;
        }
        try {
            controller.GiveBook(title, author, reader, new Date(System.currentTimeMillis()));
        } catch (ControllerException e) {
            throw new ServletException("Cannot take book");
        }
        new LoadTakeBookCommand(authMap).execute(request, response);
    }
}
