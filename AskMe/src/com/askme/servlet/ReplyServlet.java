package com.askme.servlet;

import com.askme.dao.ReplyDAO;
import com.askme.model.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import java.io.IOException;

@WebServlet("/reply")
public class ReplyServlet extends HttpServlet {

    // Add a reply to a question - must be logged in
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException {

        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;

        if (user == null) {
            response.sendRedirect("login");
            return;
        }

        int questionId = parseInt(request.getParameter("questionId"));
        String content = request.getParameter("content");

        if (questionId > 0 && content != null && !content.trim().isEmpty()) {
            ReplyDAO replyDAO = new ReplyDAO();
            replyDAO.addReply(questionId, user.getUserId(), content.trim());
        }
        response.sendRedirect("question?questionId=" + questionId);
    }

    private int parseInt(String s) {
        try { return Integer.parseInt(s); } catch (Exception e) { return 0; }
    }
}