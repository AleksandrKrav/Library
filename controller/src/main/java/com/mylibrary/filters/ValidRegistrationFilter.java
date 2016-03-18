package com.mylibrary.filters;

import com.mylibrary.PersistException;
import com.mylibrary.User;
import com.mylibrary.impl.UserService;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidRegistrationFilter implements Filter {

    private Pattern pattern;
    private Matcher matcher;

    private static final String LOGIN_PATTERN = "^[a-zA-Z0-9_-]{3,15}$";
    private static final String PASSWORD_PATTERN = "([a-zA-Z0-9_-].{5,20})";
    private UserService userService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        userService = new UserService();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String login = request.getParameter("reg-login");
        String password = request.getParameter("reg-pass");
        if (!validUserLogin(login)) {
            response.sendRedirect("/pages/registration.jsp?errorUserLogin=1");
            return;
        }
        if (!validPassword(password)) {
            response.sendRedirect("/pages/registration.jsp?errorPass=1");
            return;
        }

        try {
            if (!validSameUser(login)) {
                response.sendRedirect("/pages/registration.jsp?errorSameUserLogin=1");
                return;
            }
            if (validUserLogin(login) && validSameUser(login) && validPassword(password)) {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        } catch (PersistException e) {
            e.printStackTrace();
        }
    }

    private boolean validSameUser(String login) throws PersistException {
        List<User> users = userService.getAll();
        for (User u : users) {
            if (u.getLogin().equals(login)) {
                return false;
            }
        }
        return true;
    }

    private boolean validUserLogin(String userLogin) {
        pattern = Pattern.compile(LOGIN_PATTERN);
        matcher = pattern.matcher(userLogin);
        return matcher.matches();
    }

    private boolean validPassword(String password) {
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();
    }

    @Override
    public void destroy() {

    }
}
