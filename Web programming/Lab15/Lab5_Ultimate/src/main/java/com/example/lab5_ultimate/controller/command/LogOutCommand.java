package com.example.lab5_ultimate.controller.command;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
