package com.askme.dao;

import com.askme.model.Topic;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class TopicDAO{
	
	//All topics for a given subject
	public List<Topic> getTopicsBySubject(int subjectId){
		List<Topic> list = new ArrayList<>();
		
		String sql = "SELECT t.*, u.username, s.name AS subject_name " +
                     "FROM topics t " +
                     "JOIN users u ON t.created_by = u.user_id " +
                     "JOIN subjects s ON t.subject_id = s.subject_id " +
                     "WHERE t.subject_id = ? ORDER BY t.created_at DESC";
		
		try(Connection con = DBConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sql)){
				
				ps.setInt(1, subjectId);
				ResultSet rs = ps.executeQuery(); 
					
				while (rs.next()){
					Topic t =buildTopic(rs);
					list.add(t);
					}
					
		}catch(SQLException e){
		    System.out.println("getTopicsBySubject error: "+ e.getMessage());
		}
        return list;			
	}	
		//This avoids repeating the same code in multiple methods.
		//buildTopic(rs), you write it once and reuse it anywhere you need to convert a ResultSet row into a Topic object
	
	
	//All topics created by a user in my posts
	public List<Topic> getTopicsByUser(int userId){
		List<Topic> list = new ArrayList<>();
		
		String sql = "SELECT t.*, u.username, s.name AS subject_name " +
                     "FROM topics t " +
                     "JOIN users u ON t.created_by = u.user_id " +
                     "JOIN subjects s ON t.subject_id = s.subject_id " +
                     "WHERE t.created_by = ? ORDER BY t.created_at DESC";
		
		try(Connection con = DBConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sql)){
				
				
				ps.setInt(1,userId);
				ResultSet rs = ps.executeQuery(); 
					
				while (rs.next()){
					list.add(buildTopic(rs));
					}
					
		}catch(SQLException e){
		    System.out.println("getTopicsByUser error: "+ e.getMessage());
		}
        return list;
	}
	
	
	//Single topic by Id 
	public Topic getTopicById(int topicId){
	
		String sql = "SELECT t.*, u.username, s.name AS subject_name " +
                     "FROM topics t " +
                     "JOIN users u ON t.created_by = u.user_id " +
                     "JOIN subjects s ON t.subject_id = s.subject_id " +
                     "WHERE t.topic_id = ?";
		
		try(Connection con = DBConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sql)){
				
				
				ps.setInt(1, topicId);
				ResultSet rs = ps.executeQuery(); 
					
				if (rs.next())
					return buildTopic(rs);
					
					
		}catch(SQLException e){
		    System.out.println("getTopicsById error: "+ e.getMessage());
		}
        return null;
	}
	
	//add new topic
	public boolean addTopic(int subjectId, String title, int createdBy) {
		String sql ="INSERT INTO topics (subject_id, title, created_by) VALUES(?,?,?)";
		
		try(Connection con = DBConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sql)){
				
				ps.setInt(1, subjectId);
				ps.setString(2, title);
				ps.setInt(3, createdBy);
				ps.executeUpdate();
				return true;
		}catch(SQLException e){
		    System.out.println("addTopic error: "+ e.getMessage());
			return false;
		}
    }
	
	//Search topics by keyword
	public List<Topic> searchTopics(String keyword){
		List<Topic> list = new ArrayList<>();
		
            String sql = "SELECT t.*, u.username, s.name AS subject_name " +
                     "FROM topics t " +
                     "JOIN users u ON t.created_by = u.user_id " +
                     "JOIN subjects s ON t.subject_id = s.subject_id " +
                     "WHERE t.title LIKE ? ORDER BY t.created_at DESC";
		
		try(Connection con = DBConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sql)){
				
				
				ps.setString(1, "%" + keyword + "%");
				ResultSet rs = ps.executeQuery(); 
					
				while (rs.next()){
					list.add(buildTopic(rs));
					}
					
		}catch(SQLException e){
		    System.out.println("searchTopics error: "+ e.getMessage());
		}
        return list;
	}
	
	private Topic buildTopic(ResultSet rs) throws SQLException {
        Topic t = new Topic();
        t.setTopicId(rs.getInt("topic_id"));
        t.setSubjectId(rs.getInt("subject_id"));
        t.setSubjectName(rs.getString("subject_name"));
        t.setTitle(rs.getString("title"));
        t.setCreatedBy(rs.getInt("created_by"));
        t.setCreatedByUsername(rs.getString("username"));
        t.setCreatedAt(rs.getString("created_at"));
        return t;
    }
	
}