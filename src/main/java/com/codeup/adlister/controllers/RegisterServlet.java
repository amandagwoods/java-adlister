package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "controllers.RegisterServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO: show the registration form
       //makes so that the error message doesn't recur in session after fixed
        HttpSession session = request.getSession();
        String errorMessage = (String).getAttribute("registerError");
        if(errorMessage != null){
            request.setAttribute("registerError", errorMessage);
            session.removeAttribute("registerError");
        }

        request. getRequestDispatcher("/WEB-INF/register.jsp").forward(request,response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // TODO: if a user was successfully created, send them to their profile
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("passowrd");
        String confirm = request.getParameter("confirm");


        boolean inputHasErrors = username.isEmpty()
                || email.isEmpty()
                || password.isEmpty()
                || confirm.isEmpty()
                || (!password.equals(confirm));
        if(inputHasErrors){
            request.getSession().setAttribute("registerError", "There was an error with your registration.");
            response.sendRedirect("/register");
            return;
        }
        // TODO: create a new user based off of the submitted information

        User user = new User(username, email, password);
        DaoFactory.getUsersDao().insert(user);
        response.sendRedirect("/login");

        // TODO: ensure the submitted information is valid




    }
}
