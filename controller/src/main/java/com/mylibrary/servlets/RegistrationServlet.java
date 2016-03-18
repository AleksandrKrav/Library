package com.mylibrary.servlets;

import com.mylibrary.PersistException;
import com.mylibrary.User;
import com.mylibrary.impl.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("registr") != null) {
            User user = new User();
            user.setName(req.getParameter("reg-name"));
            user.setLogin(req.getParameter("reg-login"));
            user.setPassword(req.getParameter("reg-pass"));
            try {
                user.setRole(userService.getRoleById(2));
                userService.add(user);
            } catch (PersistException e) {
                e.printStackTrace();
            }
            resp.sendRedirect("/pages/login.jsp");
        }
    }

}
