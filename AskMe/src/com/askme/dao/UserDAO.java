package com.askme.dao;

import com.askme.model.User;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;

public class UserDAO{
	
	//registering a new user in your forum
	public boolean registerUser(String username, String email, String plainPassword){
		
		String sql ="INSERT INTO users (username, email, password) VALUES (?,?,?)";
		String hashedPassword = PasswordUtil.hashPassword(plainPassword);
		
		try(Connection con = DBConnection.getConnection();
		    PreparedStatement ps = con.prepareStatement(sql)) {
				
				ps.setString(1, username);
				ps.setString(2,email);
				ps.setString(3, hashedPassword);
				
				ps.executeUpdate();
				 return true;	
				 
			}catch (SQLException e){
				System.out.println("registerUser error: "+ e.getMessage());
				return false;
			}
	}
	
    // Check if username already exists
	public boolean usernameExists(String username){
		String sql ="SELECT user_id FROM users WHERE username =?";
		
		try(Connection con = DBConnection.getConnection();
		    PreparedStatement ps = con.prepareStatement(sql)) {
				
				ps.setString(1, username);
				ResultSet rs = ps.executeQuery();
				 return rs.next();		 
			}catch (SQLException e){
				System.out.println("usernameExists error: "+ e.getMessage());
				return false;
			}
	}
	
	//check if email already exists
	public boolean emailExists(String email){
		String sql ="SELECT user_id FROM users WHERE email =?";
		
		try(Connection con = DBConnection.getConnection();
		    PreparedStatement ps = con.prepareStatement(sql)) {
				
				ps.setString(1, email);
				ResultSet rs = ps.executeQuery();
				 return rs.next();	
				 
			}catch (SQLException e){
				System.out.println("emailExists error: "+ e.getMessage());
				return false;
			}
	}
	
	//Login
	public User loginUser(String username,String plainPassword){
		String sql= " SELECT * FROM users WHERE username =?";
		
			try(Connection con = DBConnection.getConnection();
				PreparedStatement ps = con.prepareStatement(sql)) {
				
				ps.setString(1, username);
				ResultSet rs = ps.executeQuery();
				//resudtset has a row
				
				//Check if User Exists
				//rs.next() moves to the first row.
				if(rs.next()){
					String storedHash = rs.getString("password");
					
					System.out.println("Username = " + username);
    
					if(BCrypt.checkpw(plainPassword,storedHash)){
						User user = new User();
						user.setUserId(rs.getInt("user_id"));
						user.setUsername(rs.getString("username"));
						user.setEmail(rs.getString("email"));
						user.setPassword(storedHash);
						user.setCreatedAt(rs.getString("created_at"));
						return user;
					}
				}
		    }catch(SQLException e){
				System.out.println("loginUser error: "+ e.getMessage());
			}
            return null;			
	}
	
	
	

	//Update password using username
	public boolean updatePasswordByUsername(String username, String newPlainPassword) {

    String sql = "UPDATE users SET password = ? WHERE username = ?";

    String hashedPassword = PasswordUtil.hashPassword(newPlainPassword);

    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, hashedPassword);
        ps.setString(2, username);

        int rows = ps.executeUpdate();
		

        return rows > 0;

    } catch (SQLException e) {
        System.out.println("updatePasswordByUsername error: " + e.getMessage());
    }

    return false;
    }
	
	
	
	// Verify current password for a user (used by change password flow)
    public boolean verifyPassword(int userId, String plainPassword) {
        String sql = "SELECT password FROM users WHERE user_id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String storedHash = rs.getString("password");
                return PasswordUtil.checkPassword(plainPassword, storedHash);
            }

        } catch (SQLException e) {
            System.out.println("verifyPassword error: " + e.getMessage());
        }
        return false;
    }
	
	
	// Update a user's password (used by change password flow)
    public boolean updatePassword(int userId, String newPlainPassword) {
        String sql = "UPDATE users SET password = ? WHERE user_id = ?";
        String hashedPassword = PasswordUtil.hashPassword(newPlainPassword);

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, hashedPassword);
            ps.setInt(2, userId);
            int rows = ps.executeUpdate();
			//Checks whether the update was successful.
            return rows > 0;

        } catch (SQLException e) {
            System.out.println("updatePassword error: " + e.getMessage());
            return false;
        }
    }
	
}

	

