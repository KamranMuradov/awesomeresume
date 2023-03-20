package org.company.resume.controller;

import org.company.dao.inter.UserDaoInter;
import org.company.entity.User;
import org.company.main.Context;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserController", value = "/users")
public class UserController extends HttpServlet {

    private UserDaoInter userDao = Context.instanceUserDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            String nationalityIdStr = request.getParameter("nid");
            Integer nationalityId = null;
            if (nationalityIdStr != null && !nationalityIdStr.trim().isEmpty()) {
                nationalityId = Integer.parseInt(nationalityIdStr);
            }
            List<User> userList = userDao.getAll(name, surname, nationalityId);
            if (userList.size() == 0) {
                throw new IllegalArgumentException("there is no user with this search parameter");
            }
            request.setAttribute("users", userList);
            request.getRequestDispatcher("users.jsp").forward(request, response);
        } catch (Exception e) {
            response.sendRedirect("error?msg=" + e.getMessage());
        }
    }
}
