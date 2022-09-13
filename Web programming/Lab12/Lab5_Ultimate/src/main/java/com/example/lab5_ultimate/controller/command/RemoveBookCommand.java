package com.example.lab5_ultimate.controller.command;

import com.example.lab5_ultimate.controller.LibraryController;
import com.example.lab5_ultimate.model.exception.ControllerException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor
public class RemoveBookCommand implements Command {

    Map<String, String> authMap;
    LibraryController controller;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        if(authMap == null) {
            new LoadLoginCommand().execute(request, response);
            return;
        }

        int bookId = Integer.parseInt(request.getParameter("bookId"));
        String reader = authMap.getOrDefault("user", "");

        if (reader.equals("")) {
            new LoadLoginCommand().execute(request, response);
            return;
        }
        try {
            controller.deleteBook(bookId);
        } catch (ControllerException e) {
            throw new ServletException("Cannot take book");
        }
        new LoadNumbersCommand(authMap).execute(request, response);
    }
}
