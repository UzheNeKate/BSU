package com.example.lab5_ultimate.controller.command;

import com.example.lab5_ultimate.controller.LibraryController;
import com.example.lab5_ultimate.model.exception.ControllerException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor
public class RegistrationCommand implements Command{
    LibraryController controller;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        try {
            controller.Register(name, password);
        } catch (ControllerException e) {
            response.setStatus(401);
            return;
        }
        new LoadIndexCommand(Map.of("user", name, "role", "user")).execute(request, response);
    }
}
