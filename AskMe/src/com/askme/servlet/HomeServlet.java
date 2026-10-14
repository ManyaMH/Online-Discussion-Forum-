package com.askme.servlet;

import com.askme.model.User;
import com.askme.model.Subject;
import com.askme.dao.SubjectDAO;
import com.askme.dao.QuestionDAO;
import com.askme.model.Question;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.io.IOException;
import javax.servlet.ServletException;

@WebServlet("/home")
public class HomeServlet extends HttpServlet{
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		// Random questions for the main feed
        QuestionDAO questionDAO = new QuestionDAO();
        List<Question> randomQuestions = questionDAO.getRandomQuestions(20);
        request.setAttribute("questions", randomQuestions);
		
		//All subject
		SubjectDAO subjectDAO = new SubjectDAO();
		List<Subject> subjects = subjectDAO.getAllSubjects();
		request.setAttribute("subjects",subjects);
		
		//Logged-in user
		HttpSession session = request.getSession(false);
		User user =(session != null)?(User)session.getAttribute("user"):null;
		request.setAttribute("user",user);
		
		request.getRequestDispatcher("/home.jsp").forward(request, response);
		
	}
}