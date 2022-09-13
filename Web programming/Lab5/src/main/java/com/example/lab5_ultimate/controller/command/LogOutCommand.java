package com.example.lab5_ultimate.controller.command;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor
public class LogOutCommand implements Command{
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        HttpSession session = request.getSession();
        session.setAttribute("role", null);
        session.setAttribute("user", null);
        new LoadIndexCommand(null).execute(request,response);
    }
}
