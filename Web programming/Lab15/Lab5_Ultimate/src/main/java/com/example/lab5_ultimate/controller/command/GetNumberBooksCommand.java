package com.example.lab5_ultimate.controller.command;

import com.example.lab5_ultimate.controller.LibraryController;
import com.example.lab5_ultimate.model.entity.BookEntity;
import com.example.lab5_ultimate.model.exception.ControllerException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class GetNumberBooksCommand implements Command {
    Map<String, String> authMap;
    LibraryController controller;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        String author = request.getParameter("author");
        String title = request.getParameter("title");

        try {
            BookEntity book = controller.GetNumberOfBook(title, author);
            request.setAttribute("allBooks", List.of(book));
        } catch (ControllerException e) {
            throw new ServletException("Cannot load number of books");
        }
        new LoadNumbersCommand(authMap).execute(request, response);
    }
}
