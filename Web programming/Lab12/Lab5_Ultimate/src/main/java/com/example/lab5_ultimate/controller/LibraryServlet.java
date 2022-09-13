package com.example.lab5_ultimate.controller;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.example.lab5_ultimate.controller.command.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet(name = "libraryServlet", value = "/library-servlet")
public class LibraryServlet extends HttpServlet {
    private LibraryController controller;

    protected Logger logger = LogManager.getLogger();


    public void init() {
        controller = new LibraryController();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        String action = request.getParameter("action");
        Map<String, String> authMap = getAuthCookiesOrNull(request);
        if (action == null) {
            startNewSessionAndSaveCookies(request, response);
            try {
                new LoadIndexCommand(authMap).execute(request, response);
            } catch (ServletException e) {
                log(e.getMessage());
                logger.error(e.getMessage());
            }
            return;
        }
        Command cmd = null;
        switch (action) {
            case "log_out":
                cmd = new LogOutCommand();
                break;
            case "take_book":
                cmd = new LoadTakeBookCommand(authMap);
                break;
            case "load_debtors":
                cmd = new LoadDebtorsCommand(authMap, controller);
                break;
            case "load_number":
                cmd = new LoadNumbersCommand(authMap);
                break;
            case "get_number":
                cmd = new GetNumberBooksCommand(authMap, controller);
                break;
            case "load_search":
                cmd = new LoadSearchCommand(authMap);
                break;
            case "search_books":
                cmd = new GetBooksByAuthorCommand(authMap, controller);
                break;
            case "chat":
                cmd = new LoadChatCommand(authMap);
                break;
        }
        try {
            cmd.execute(request, response);
        } catch (ServletException | NullPointerException e) {
            log(e.getMessage());
            logger.error(e.getMessage());
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html");
        String action = request.getParameter("action");
        Map<String, String> authMap = getAuthCookiesOrNull(request);
        if (action == null) {
            try {
                new LoadIndexCommand(authMap).execute(request, response);
            } catch (ServletException e) {
                log(e.getMessage());
                logger.error(e.getMessage());
            }
            return;
        }
        Command cmd = null;
        switch (action) {
            case "take-book":
                cmd = new TakeBookCommand(authMap, controller);
                break;
            case "login":
                cmd = new LoginCommand(controller);
                break;
            case "registration":
                cmd = new RegistrationCommand(controller);
                break;
            case "remove_book":
                cmd = new RemoveBookCommand(authMap, controller);
        }
        try {
            cmd.execute(request, response);
        } catch (ServletException | NullPointerException e) {
            log(e.getMessage());
            logger.error(e.getMessage());
        }
    }

    public void destroy() {

    }

    private void startNewSessionAndSaveCookies(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        Cookie lastEnterTime = new Cookie("lastEnterTime",
                new SimpleDateFormat("yyyy-MM-dd-hh:mm").format(new Date()));
        lastEnterTime.setComment("Информация о времени и дате последнего сеанса пользователя,");
        Cookie usageCount = new Cookie("usageCount", "1");
        usageCount.setComment("Информация о количестве посещений ресурса.");

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("usageCount")) {
                    int lastUsageCount = Integer.parseInt(cookie.getValue());
                    lastUsageCount += 1;
                    usageCount.setValue(Integer.toString(lastUsageCount));
                }
            }
        }
        response.addCookie(lastEnterTime);
        response.addCookie(usageCount);
    }

    private Map<String, String> getAuthCookiesOrNull(HttpServletRequest request) {
        HttpSession session = request.getSession();

        Map<String, String> authMap = new HashMap<>();
        authMap.put("user", (String) session.getAttribute("user"));
        authMap.put("role", (String) session.getAttribute("role"));

        return authMap;
    }
}