package com.askme.dao;

import com.askme.model.Question;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionDAO {
	
	//all questions in a topic
	public List<Question> getQuestionsByTopic(int topicId) {
        List<Question> list = new ArrayList<>();
		//count reply, This gets the username, Gets the topic title.
        String sql = "SELECT q.*, u.username, t.title AS topic_title, " +
                     "(SELECT COUNT(*) FROM replies r WHERE r.question_id = q.question_id) AS reply_count " +
					 "FROM questions q " +
					 "JOIN users u ON q.user_id = u.user_id " +
                     "JOIN topics t ON q.topic_id = t.topic_id " +
					 "WHERE q.topic_id = ? ORDER BY q.created_at DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, topicId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(buildQuestion(rs));
            }

        } catch (SQLException e) {
            System.out.println("getQuestionsByTopic error: " + e.getMessage());
        }
        return list;
    }
	
	
	// Random questions for home page
    public List<Question> getRandomQuestions(int limit) {
        List<Question> list = new ArrayList<>();
        String sql = "SELECT q.*, u.username, t.title AS            topic_title, " +
                     "(SELECT COUNT(*) FROM replies r WHERE r.question_id = q.question_id) AS reply_count " +
					 "FROM questions q " +
                     "JOIN users u ON q.user_id = u.user_id " +
	                 "JOIN topics t ON q.topic_id = t.topic_id " +
					 "ORDER BY RAND() LIMIT ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
			
			ps.setInt(1, limit);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(buildQuestion(rs));
            }

        } catch (SQLException e) {
            System.out.println("getRandomQuestions error: " + e.getMessage());
        }
        return list;
    }
	
	// All questions by user -My Posts page
    public List<Question> getQuestionsByUser(int userId) {
        List<Question> list = new ArrayList<>();
        String sql = "SELECT q.*, u.username, t.title AS topic_title, " +
		
                     "(SELECT COUNT(*) FROM replies r WHERE r.question_id = q.question_id) AS reply_count " +
					 "FROM questions q " +
					 "JOIN users u ON q.user_id = u.user_id " +
					 "JOIN topics t ON q.topic_id = t.topic_id " +
					 "WHERE q.user_id = ? ORDER BY q.created_at DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(buildQuestion(rs));
            }

        } catch (SQLException e) {
            System.out.println("getQuestionsByUser error: " + e.getMessage());
        }
        return list;
    }
	
	
	// Single question by ID
    public Question getQuestionById(int questionId) {
        String sql = "SELECT q.*, u.username, t.title AS topic_title, " +
                     "(SELECT COUNT(*) FROM replies r WHERE r.question_id = q.question_id) AS reply_count " +
                     "FROM questions q " +
					 "JOIN users u ON q.user_id = u.user_id " +
					 "JOIN topics t ON q.topic_id = t.topic_id " +
					 "WHERE q.question_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, questionId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) 
				return buildQuestion(rs);

        } catch (SQLException e) {
            System.out.println("getQuestionById error: " + e.getMessage());
        }
        return null;
    }
	
	    // Add a question
    public boolean addQuestion(int topicId, int userId, String content) {
        String sql = "INSERT INTO questions (topic_id, user_id, content) VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, topicId);
            ps.setInt(2, userId);
            ps.setString(3, content);
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("addQuestion error: " + e.getMessage());
            return false;
        }
    }
	
	
	// Search questions by keyword
    public List<Question> searchQuestions(String keyword) {
        List<Question> list = new ArrayList<>();
        String sql = "SELECT q.*, u.username, t.title AS topic_title, " +
                     "(SELECT COUNT(*) FROM replies r WHERE r.question_id = q.question_id) AS reply_count " +
                     "FROM questions q " +
                     "JOIN users u ON q.user_id = u.user_id " +
                     "JOIN topics t ON q.topic_id = t.topic_id " +
                     "WHERE q.content LIKE ? ORDER BY q.created_at DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(buildQuestion(rs));
            }

        } catch (SQLException e) {
            System.out.println("searchQuestions error: " + e.getMessage());
        }
        return list;
    }

    private Question buildQuestion(ResultSet rs) throws SQLException {
        Question q = new Question();
        q.setQuestionId(rs.getInt("question_id"));
        q.setTopicId(rs.getInt("topic_id"));
        q.setTopicTitle(rs.getString("topic_title"));
        q.setUserId(rs.getInt("user_id"));
        q.setUsername(rs.getString("username"));
        q.setContent(rs.getString("content"));
        q.setCreatedAt(rs.getString("created_at"));
        q.setReplyCount(rs.getInt("reply_count"));
        return q;
    }
}
