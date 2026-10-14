package com.askme.servlet;

import com.askme.dao.UserDAO;
//import com.askme.model.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import javax.servlet.ServletException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet{
	
	//show signup page
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		request.getRequestDispatcher("/register.jsp").forward(request,response);	
	}
	
	//form submission
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		request.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		UserDAO userDAO = new UserDAO();
		
		//validation if there is a empty or whitespace characters
		if(username == null || username.trim().isEmpty() ||
		   email == null || email.trim().isEmpty() || 
		   password == null || password.trim().isEmpty()) {
			   
			   request.setAttribute("error","All fields are required.");
			   request.getRequestDispatcher("/register.jsp").forward(request,response);
			   return;
		   }
		   
		   
		   //checks if the name and email exists
		   if(userDAO.usernameExists(username)){
			   request.setAttribute("error","That username is already taken.");
			   request.getRequestDispatcher("/register.jsp").forward(request,response);
			   return;
		   }
		   
		   if(userDAO.emailExists(email)){
			   request.setAttribute("error","That email is already taken.");
			   request.getRequestDispatcher("/register.jsp").forward(request,response);
			   return;
		   }
		   
		   boolean ok = userDAO.registerUser(username, email, password);
		   if(ok){
			   request.setAttribute("success","Account created! please log in .");
			   request.getRequestDispatcher("/login.jsp").forward(request,response);
			   return;
		   } else {
			   request.setAttribute("error","Someting went wrong Try again.");
			   request.getRequestDispatcher("/register.jsp").forward(request,response);
		   }

	}
}

