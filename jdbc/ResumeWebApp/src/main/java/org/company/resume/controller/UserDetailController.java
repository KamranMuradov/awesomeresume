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
import java.sql.Date;

@WebServlet(name = "UserDetailController", value = "/userdetail")
public class UserDetailController extends HttpServlet {
    private UserDaoInter userDao = Context.instanceUserDao();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.valueOf(request.getParameter("id"));
        String action = request.getParameter("action");
        if (action.equals("update")) {
            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            String birthdate = request.getParameter("birthdate");
            String profileDesc = request.getParameter("profile description");


            System.out.println("name = " + name);
            System.out.println("surname = " + surname);
            User user = userDao.getById(id);
            user.setName(name);
            user.setSurname(surname);
            user.setEmail(email);
            user.setPhone(phone);
            user.setAddress(address);
            user.setBirthdate(Date.valueOf(birthdate));
            user.setProfileDesc(profileDesc);
            userDao.updateUser(user);
        } else if (action.equals("delete")) {
            userDao.removeUser(id);
        }
        response.sendRedirect("users");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String userIdStr = request.getParameter("id");
            if (userIdStr == null || userIdStr.trim().isEmpty()) {
                throw new IllegalArgumentException("id is not specified");
            }

            Integer userId = Integer.parseInt(userIdStr);
//            UserDaoInter userDao = Context.instanceUserDao();
            User user = userDao.getById(userId);
            if (user == null) {
                throw new IllegalArgumentException("There is no user with this id");
            }
            request.setAttribute("user", user);
            request.getRequestDispatcher("userdetail.jsp").forward(request, response);
        } catch (Exception e) {
            response.sendRedirect("error?msg=" + e.getMessage());
        }
    }
}
