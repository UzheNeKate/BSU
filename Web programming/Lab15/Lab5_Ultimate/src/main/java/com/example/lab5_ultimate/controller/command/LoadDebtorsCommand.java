package com.example.lab5_ultimate.controller.command;

import com.example.lab5_ultimate.controller.LibraryController;
import com.example.lab5_ultimate.model.exception.ControllerException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.util.Map;

@AllArgsConstructor
public class LoadDebtorsCommand implements Command {
    Map<String, String> authMap;
    LibraryController controller;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        if (authMap == null) {
            new LoadLoginCommand().execute(request, response);
            return;
        }
        if(!authMap.get("role").equals("admin")){
            response.setStatus(403);
            return;
        }
        request.setAttribute("role", authMap == null ? "guest" : authMap.getOrDefault("role", "guest"));
        try {
            request.setAttribute("debtors", controller.GetDebtors());
        } catch (ControllerException e) {
            throw new ServletException("Cannot load debtors");
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("debtors/debtors.jsp");
        try {
            requestDispatcher.forward(request, response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
