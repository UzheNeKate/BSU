package com.example.lab5_ultimate.controller.filter;

import com.example.lab5_ultimate.controller.command.LoadRegistrationCommand;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

public class RegistrationFormFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String action = servletRequest.getParameter("action");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if (action == null || !action.equals("registration") || !request.getMethod().equalsIgnoreCase("POST")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        String password = servletRequest.getParameter("password");
        String name = servletRequest.getParameter("name");

        if(!name.matches("^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$")){
            //servletRequest.setAttribute("errorMessage", "Email is incorrect");
            new LoadRegistrationCommand("Email is incorrect").execute(request, (HttpServletResponse) servletResponse);
            return;
        }

        if(!password.matches("^[a-zA-Z0-9]{8,}$")){
            //servletRequest.setAttribute("errorMessage", "Password is incorrect");
            new LoadRegistrationCommand("Password is incorrect").execute(request, (HttpServletResponse) servletResponse);
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
