package com.askme.servlet;

import com.askme.dao.UserDAO;
import com.askme.model.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import javax.servlet.ServletException;

@WebServlet("/changePassword")
public class ChangePasswordServlet extends HttpServlet {

    // Show the change password page
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        //HttpSession session = request.getSession(false);
        //User user = (session != null) ? (User) session.getAttribute("user") : null;

//if (user == null) {
           // response.sendRedirect("login");
           // return;
       // }

        //request.setAttribute("user", user);
        request.getRequestDispatcher("/changePassword.jsp").forward(request, response);
    }

    // Handle the form submission
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException {

        request.setCharacterEncoding("UTF-8");
        
		String username = request.getParameter("username");

        String newPassword     = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        // Basic validation
        if (username == null || username.trim().isEmpty() ||
            newPassword == null || newPassword.trim().isEmpty() ||
            confirmPassword == null || confirmPassword.trim().isEmpty()) {

            request.setAttribute("error", "All fields are required.");
            
            request.getRequestDispatcher("/changePassword.jsp").forward(request, response);
            return;
        }

        // Check if new passwords match
        if (!newPassword.equals(confirmPassword)) {
            request.setAttribute("error", "New password and confirm password do not match.");
           // request.setAttribute("user", user);
            request.getRequestDispatcher("/changePassword.jsp").forward(request, response);
            return;
        }

        UserDAO userDAO = new UserDAO();

        
		if (!userDAO.usernameExists(username)) {

             request.setAttribute("error", "Username does not exist.");
             request.getRequestDispatcher("/changePassword.jsp").forward(request, response);
             return;
}

        // Update to the new password
        boolean ok = userDAO.updatePasswordByUsername(username, newPassword);

        if (ok) {
            request.setAttribute("success", "Password changed successfully.");
        } else {
             request.setAttribute("error", "Unable to change password.");
        }
        
        request.getRequestDispatcher("/changePassword.jsp").forward(request, response);
    }
}