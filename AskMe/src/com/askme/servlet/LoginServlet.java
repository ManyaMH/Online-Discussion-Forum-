package com.askme.servlet;

import com.askme.dao.UserDAO;
import com.askme.model.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.io.IOException;
import javax.servlet.ServletException;

@WebServlet("/login")

public class LoginServlet extends HttpServlet{
	
	//show login page
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		request.getRequestDispatcher("/login.jsp").forward(request,response);	
	}
	
	//Handle the form submission
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		request.setCharacterEncoding("UTF-8");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		UserDAO userDAO = new UserDAO();
		User user = userDAO.loginUser(username,password);
		
		if(user != null){
			HttpSession session = request.getSession();
			session.setAttribute("user",user);
			response.sendRedirect("home");	
		}else{
		   request.setAttribute("error","wrong username or password.");
           request.getRequestDispatcher("/login.jsp").forward(request, response);		   
		}
	}	
}