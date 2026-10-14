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

@WebServlet("/topic")
public class TopicServlet extends HttpServlet{
	
	// Show all topics inside subjecs + all guestions in selected topic
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
		
		//topic?subjectId=2&topicId=7
		int subjectId = parseInt(request.getParameter("subjectId"));
		int topicId = parseInt(request.getParameter("topicId"));
		
		//create DAO object
		SubjectDAO subjectDAO = new SubjectDAO();
		TopicDAO topicDAO = new TopicDAO();
		QuestionDAO questionDAO = new QuestionDAO();
		
		//Subject info
		Subject subject = subjectDAO.getSubjectById(subjectId);
		request.setAttribute("subject", subject);
		
		//topic inside this subjectDAO
		List<Topic> topics = topicDAO.getTopicsBySubject(subjectId);
        request.setAttribute("topics", topics);
		
		
		//if a toipc is selected, load the qusetion
		if(topicId > 0){
			Topic selectedTopic = topicDAO.getTopicById(topicId);
			request.setAttribute("selectedTopic", selectedTopic);
			
			List<Question> questions = questionDAO.getQuestionsByTopic(topicId);
            request.setAttribute("questions", questions);
		}
			
		HttpSession session = request.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;
        request.setAttribute("user", user);

        request.getRequestDispatcher("/topics.jsp").forward(request, response);	
	}
	
	
	//Add a new topic - logged in user
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException {

        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;

        if (user == null) {
            response.sendRedirect("login");
            return;
        }
		
		int subjectId = parseInt(request.getParameter("subjectId"));
		String title = request.getParameter("title");
		
		if(subjectId > 0 && title != null && !title.trim().isEmpty()) {
			TopicDAO topicDAO = new TopicDAO();
			topicDAO.addTopic(subjectId, title.trim(), user.getUserId());
		}
		response.sendRedirect("topic?subjectId=" + subjectId);
	}
	
	private int parseInt(String s){
		try{
			return Integer.parseInt(s);
		}catch (Exception e){
			return 0;
		}
	}
}