package org.company.resume.controller;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.company.dao.inter.UserDaoInter;
import org.company.entity.User;
import org.company.main.Context;
import org.company.resume.util.ControllerUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginController", value = "/login")
public class LoginController extends HttpServlet {
    private static BCrypt.Verifyer verifyer = BCrypt.verifyer();


    private UserDaoInter userDao = Context.instanceUserDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            String email = request.getParameter("email");
            String password = request.getParameter("password");

            User user = userDao.findByEmail(email);
            if (user == null) {
                throw new IllegalArgumentException("User doesn't exist!!!");
            }

            BCrypt.Result result = verifyer.verify(password.toCharArray(), user.getPassword().toCharArray());
            if (!result.verified) {
                throw new IllegalArgumentException("email or password is incorrect!!!");
            }
            request.getSession().setAttribute("loggedInUser", user);
            response.sendRedirect("users");
        } catch (Exception e) {
            ControllerUtil.errorPage(response, e);
        }
    }

}
