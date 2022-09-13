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
public class GetBooksByAuthorCommand implements Command{
    Map<String, String> authMap;
    LibraryController controller;
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        String author = request.getParameter("author");

        try {
            List<BookEntity> books = controller.GetBooks(author);
            request.setAttribute("allBooks", books);
        } catch (ControllerException e) {
            throw new ServletException("Cannot load books of " + author);
        }
        new LoadSearchCommand(authMap).execute(request, response);
    }
}
