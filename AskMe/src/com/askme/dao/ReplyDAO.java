package com.askme.dao;

import com.askme.model.Reply;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReplyDAO{
	
	//all replies for a question
	public List<Reply> getRepliesByQuestion(int questionId) {
        List<Reply> list = new ArrayList<>();
        String sql = "SELECT r.*, u.username FROM replies r " +
                     "JOIN users u ON r.user_id = u.user_id " +
                     "WHERE r.question_id = ? ORDER BY r.created_at ASC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, questionId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Reply reply = new Reply();
                reply.setReplyId(rs.getInt("reply_id"));
                reply.setQuestionId(rs.getInt("question_id"));
                reply.setUserId(rs.getInt("user_id"));
                reply.setUsername(rs.getString("username"));
                reply.setContent(rs.getString("content"));
                reply.setCreatedAt(rs.getString("created_at"));
                list.add(reply);
            }

        } catch (SQLException e) {
            System.out.println("getRepliesByQuestion error: " + e.getMessage());
        }
        return list;
    }

    // Add a reply
    public boolean addReply(int questionId, int userId, String content) {
        String sql = "INSERT INTO replies (question_id, user_id, content) VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, questionId);
            ps.setInt(2, userId);
            ps.setString(3, content);
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("addReply error: " + e.getMessage());
            return false;
        }
    }
}