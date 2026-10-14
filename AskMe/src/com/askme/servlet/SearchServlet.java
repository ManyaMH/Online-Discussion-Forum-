package com.askme.servlet;

import com.askme.dao.QuestionDAO;
import com.askme.dao.SubjectDAO;
import com.askme.dao.TopicDAO;
import com.askme.model.Question;
import com.askme.model.Subject;
import com.askme.model.Topic;
import com.askme.model.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.io.IOException;
import javax.servlet.ServletException;

@WebServlet("/search")
public class SearchServlet extends HttpServlet{
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
		///read search keyword
		
		String keyword = request.getParameter("q");
		
		if(keyword != null && !keyword.trim().isEmpty()){
			keyword = keyword.trim();
			
			QuestionDAO questionDAO = new QuestionDAO();
			TopicDAO topicDAO = new TopicDAO();
			
			List<Question> questions = questionDAO.searchQuestions(keyword);
			List<Topic> topics = topicDAO.searchTopics(keyword);
			
			//send result to jsp
			request.setAttribute("questions", questions);
			request.setAttribute("topics", topics);
			request.setAttribute("keyword", keyword);	
		}
		HttpSession session = request.getSession(false);
		User user = (session != null) ? (User) session.getAttribute("user") : null;
		request.setAttribute("user", user);
		request.getRequestDispatcher("/search.jsp").forward(request, response);
		
    }
}