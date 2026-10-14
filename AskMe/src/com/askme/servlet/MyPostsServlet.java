package com.askme.servlet;

import com.askme.dao.QuestionDAO;
import com.askme.dao.TopicDAO;
import com.askme.model.Question;
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

@WebServlet("/myposts")
public class MyPostsServlet extends HttpServlet {
	
	//Show one question + replies
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
		
	    HttpSession session = request.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;

        // Must be logged in to see your own posts
        if (user == null) {
            response.sendRedirect("login");
            return;
        }

		QuestionDAO questionDAO = new QuestionDAO();
		TopicDAO topicDAO = new TopicDAO();
			
		List<Question> myQuestions = questionDAO.getQuestionsByUser(user.getUserId());
		List<Topic> myTopics = topicDAO.getTopicsByUser(user.getUserId());
			
			//send result to jsp
		request.setAttribute("myQuestions", myQuestions);
		request.setAttribute("myTopics", myTopics);
		request.setAttribute("user", user);	
		
		request.getRequestDispatcher("/myposts.jsp").forward(request, response);
	}
}