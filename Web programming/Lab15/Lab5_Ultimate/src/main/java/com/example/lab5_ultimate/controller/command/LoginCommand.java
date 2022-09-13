package com.example.lab5_ultimate.controller.command;

import com.example.lab5_ultimate.controller.LibraryController;
import com.example.lab5_ultimate.model.entity.UserEntity;
import com.example.lab5_ultimate.model.exception.ControllerException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor
public class LoginCommand implements Command{

    LibraryController controller;

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        try {
            UserEntity u = controller.verify(name, password);

            if(u != null){
                HttpSession session = request.getSession();
                session.setAttribute("user", name);
                session.setAttribute("role", u.getRole());
                new LoadIndexCommand(Map.of("user", name, "role", u.getRole())).execute(request, response);
            } else{
                new LoadLoginCommand(true).execute(request, response);
                return;
            }
        } catch (ControllerException e) {
            new LoadLoginCommand(true).execute(request, response);
            return;
        }
    }
}
