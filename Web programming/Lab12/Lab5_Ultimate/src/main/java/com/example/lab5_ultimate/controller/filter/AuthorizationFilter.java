package com.example.lab5_ultimate.controller.filter;

import com.example.lab5_ultimate.controller.LibraryController;
import com.example.lab5_ultimate.controller.command.LoadIndexCommand;
import com.example.lab5_ultimate.controller.command.LoadLoginCommand;
import com.example.lab5_ultimate.controller.command.LoginCommand;
import com.example.lab5_ultimate.controller.command.RegistrationCommand;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AuthorizationFilter implements Filter {

    LibraryController controller;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        controller = new LibraryController();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = ((HttpServletRequest) servletRequest);
        HttpServletResponse response = ((HttpServletResponse) servletResponse);

        String path = ((HttpServletRequest) servletRequest).getServletPath();
        if (path.equals("/registration/registration.jsp") || path.equals("/log-in/log-in.jsp")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        HttpSession session = request.getSession();

        Map<String, String> authMap = new HashMap<>();
        authMap.put("user", (String) session.getAttribute("user"));
        authMap.put("role", (String) session.getAttribute("role"));

        if (authMap.get("user") == null || authMap.get("role") == null) {
            String action = request.getParameter("action");
            String method = request.getMethod();
            if (action == null) {
                new LoadIndexCommand(null).execute(request, response);
            } else if (action.equals("log_out")) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else if (method.equals("POST") && action.equals("login")) {
                new LoginCommand(controller).execute(request, response);
            } else if (method.equals("GET") && action.equals("login")) {
                new LoadLoginCommand().execute(request, response);
            } else if (method.equals("POST") && action.equals("registration")) {
                new RegistrationCommand(controller).execute(request, response);
            } else {
                new LoadLoginCommand().execute(request, response);
            }
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}

