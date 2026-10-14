package com.askme.servlet;

import com.askme.dao.QuestionDAO;
import com.askme.dao.TopicDAO;
import com.askme.dao.ReplyDAO;
import com.askme.model.Question;
import com.askme.model.Topic;
import com.askme.model.User;
import com.askme.model.Reply;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.io.IOException;
import javax.servlet.ServletException;

@WebServlet("/question")
public class QuestionServlet extends HttpServlet {
	
	//Show one question + replies
	protected void doGet(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
		
		int questionId = parseInt(request.getParameter("questionId"));
		
		if (questionId > 0) {
            QuestionDAO questionDAO = new QuestionDAO();
            Question question = questionDAO.getQuestionById(questionId);
            request.setAttribute("question", question);
            
			//Check if Question Exists
            if (question != null) {            
                TopicDAO topicDAO = new TopicDAO();
                Topic topic = topicDAO.getTopicById(question.getTopicId());
                request.setAttribute("topic", topic);
				
				//all replies for this qustion
				ReplyDAO replyDAO = new ReplyDAO();

                List<Reply> replies =
                        replyDAO.getRepliesByQuestion(questionId);

                request.setAttribute("replies", replies);
            }
        }

        HttpSession session = request.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;
        request.setAttribute("user", user);

        request.getRequestDispatcher("/question.jsp").forward(request, response);
    }
	
	//add a new question
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
		
		request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;

        if (user == null) {
            response.sendRedirect("login");
            return;
        }
		int topicId = parseInt(request.getParameter("topicId"));
        String content = request.getParameter("content");

        if (topicId > 0 && content != null && !content.trim().isEmpty()) {
            QuestionDAO questionDAO = new QuestionDAO();
            questionDAO.addQuestion(topicId, user.getUserId(), content.trim());
        }
        response.sendRedirect("topic?subjectId=" + request.getParameter("subjectId") + "&topicId=" + topicId);
    }

    private int parseInt(String s) {
        try { return Integer.parseInt(s); } catch (Exception e) { return 0; }
    
	}
}