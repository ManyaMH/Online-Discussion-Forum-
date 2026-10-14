package com.askme.dao;

import com.askme.model.Subject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubjectDAO{
	
	//get all subject
	public List<Subject>getAllSubjects(){
		List<Subject> list = new ArrayList<>();
		
		String sql= " SELECT s.*, u.username FROM subjects s " + "LEFT JOIN users u ON s.created_by = u.user_id " + "ORDER BY s.name ASC";
		
			try(Connection con = DBConnection.getConnection();
				
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql)) {
					
				while(rs.next()){
						Subject sub = new Subject();
						sub.setSubjectId(rs.getInt("subject_id"));
						sub.setName(rs.getString("name"));
						sub.setCreatedBy(rs.getInt("created_by"));
						sub.setCreatedByUsername(rs.getString("username"));
						sub.setCreatedAt(rs.getString("created_at"));
						list.add(sub);
					}
					
		    }catch(SQLException e){
				System.out.println("getAllSubjects error: "+ e.getMessage());
			}
            return list;			
	}
	
	
	//get one subject
	public Subject getSubjectById(int subjectId){
		
		String sql= "SELECT s.*, u.username FROM subjects s " + 
		            "LEFT JOIN users u ON s.created_by = u.user_id " + 
					"WHERE s.subject_id = ?";
		
			try(Connection con = DBConnection.getConnection();
				
				PreparedStatement ps = con.prepareStatement(sql)){
				
				
				ps.setInt(1,subjectId);
				ResultSet rs = ps.executeQuery(); 
					
				if(rs.next()){
						Subject sub = new Subject();
						sub.setSubjectId(rs.getInt("subject_id"));
						sub.setName(rs.getString("name"));
						sub.setCreatedBy(rs.getInt("created_by"));
						sub.setCreatedByUsername(rs.getString("username"));
						sub.setCreatedAt(rs.getString("created_at"));
						return sub;
					}
					
		    }catch(SQLException e){
				System.out.println("getSubjectById error: "+ e.getMessage());
			}
            return null;			
	}
	
	//Add new subject
	public boolean addSubject(String name, int createdBy) {
		
		String sql ="INSERT INTO subjects(name, created_by) VALUES(?,?)";
		
		try(Connection con = DBConnection.getConnection();
				
				PreparedStatement ps = con.prepareStatement(sql)){
				
				
				ps.setString(1,name);
				ps.setInt(2, createdBy);
				ps.executeUpdate(); 
				return true;
					
					
		}catch(SQLException e){
			System.out.println("addSubject error: "+ e.getMessage());
			return false;
	    }
		
	}
}
