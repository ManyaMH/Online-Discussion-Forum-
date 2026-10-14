package com.askme.servlet;

import com.askme.dao.SubjectDAO;
import com.askme.model.Subject;
import com.askme.model.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import java.util.List;
import java.io.IOException;

@WebServlet("/subjects")
public class SubjectServlet extends HttpServlet{
	
	// Show all subjects
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
		
		SubjectDAO subjectDAO = new SubjectDAO();
		List<Subject> subjects = subjectDAO.getAllSubjects();
		//Send Subjects to JSP; <c:forEach var="s" items="${subjects}">
		request.setAttribute("subjects", subjects);
		
		HttpSession session = request.getSession(false);
		User user =(session != null)? (User) session.getAttribute("user"):null;
		request.setAttribute("user", user);
		
		request.getRequestDispatcher("/subjects.jsp").forward(request, response);
	}
	
	// Add a new subject (must be logged in)
	//Runs when someone submits the Add Subject form.
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);
		User user =(session != null)? (User) session.getAttribute("user"):null;
	
	
	    //if nobody is logged in 
	    if(user == null){
	    	response.sendRedirect("login");
	    	return;
	    }
	    String  name= request.getParameter("name");
	    if(name != null && !name.trim().isEmpty()) {
	    	SubjectDAO subjectDAO =new SubjectDAO();
	    	subjectDAO.addSubject(name.trim(), user.getUserId());
	    }
	    response.sendRedirect("subjects");
        
    }
	
}