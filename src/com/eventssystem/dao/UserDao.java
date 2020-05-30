package com.eventssystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.eventssystem.model.User;
import com.eventssystem.utility.ConnectionProvider;

public class UserDao {

    private final static String CREATE = "INSERT INTO user(userId, username, email, password, userTimestamp, active) VALUES(?, ?, ?, ?, ?, ?);";
    private final static String READ = "SELECT userId, username, email, password, userTimestamp, active FROM user WHERE userId=?;";
    private final static String UPDATE = "UPDATE user SET username=?, email=?, password=?, active=? WHERE userId=?;";
    private final static String DELETE = "DELETE FROM user WHERE userId=?;";
	private final static String USER_ROLE = "INSERT INTO user_role(username) VALUES(?);";
	private final static String GET_USER_BY_USERNAME = "SELECT userId, username, email, password, userTimestamp, active FROM user WHERE username=?;";
    
    public void create(User user) throws SQLException {
        try (Connection conn = ConnectionProvider.getConnection();
        PreparedStatement prepStmt = conn.prepareStatement(CREATE)) {
            prepStmt.setLong(1, user.getUserId());
            prepStmt.setString(2, user.getUsername());
            prepStmt.setString(3, user.getEmail());
            prepStmt.setString(4, user.getPassword());
            prepStmt.setTimestamp(5, user.getUserTimestamp());
            prepStmt.setBoolean(6, user.getActive());
            prepStmt.executeUpdate();
            setRole(user);
        }
    }

    public User read(Long userId) throws SQLException {
        User resultUser = null;
        try (Connection conn = ConnectionProvider.getConnection();
        PreparedStatement prepStmt = conn.prepareStatement(READ);) {
            prepStmt.setLong(1, userId);
            ResultSet resultSet = prepStmt.executeQuery();
            if (resultSet.next()) {
                resultUser = new User();
                resultUser.setUserId(resultSet.getLong("userId"));
                resultUser.setUsername(resultSet.getString("username"));
                resultUser.setEmail(resultSet.getString("email"));
                resultUser.setPassword(resultSet.getString("password"));
                resultUser.setUserTimestamp(resultSet.getTimestamp("userTimestamp"));
                resultUser.setActive(resultSet.getBoolean("active"));
            }
        }
        return resultUser;
    }

    public void update(User user) throws SQLException {
        try (Connection conn = ConnectionProvider.getConnection();
        PreparedStatement prepStmt = conn.prepareStatement(UPDATE);) {
            prepStmt.setString(1, user.getUsername());
            prepStmt.setString(2, user.getEmail());
            prepStmt.setString(3, user.getPassword());
            prepStmt.setBoolean(4, user.getActive());
            prepStmt.executeUpdate();
        }
    }
    
    public void delete(User user) throws SQLException {
        try (Connection conn = ConnectionProvider.getConnection();
        PreparedStatement prepStmt = conn.prepareStatement(DELETE);) {
            prepStmt.setLong(1, user.getUserId());
            prepStmt.executeUpdate();
        }
    }
    
	private void setRole(User user) throws SQLException {
		try (Connection conn = ConnectionProvider.getConnection();
        PreparedStatement prepStmt = conn.prepareStatement(USER_ROLE)) {
            prepStmt.setString(1, user.getUsername());
            prepStmt.executeUpdate();
		}
	} 
    
	
    public User getUserByUsername(String username) throws SQLException {
        User resultUser = null;
        try (Connection conn = ConnectionProvider.getConnection();
        PreparedStatement prepStmt = conn.prepareStatement(GET_USER_BY_USERNAME);) {
            prepStmt.setString(1, username);
            ResultSet resultSet = prepStmt.executeQuery();
            if (resultSet.next()) {
                resultUser = new User();
                resultUser.setUserId(resultSet.getLong("userId"));
                resultUser.setUsername(resultSet.getString("username"));
                resultUser.setEmail(resultSet.getString("email"));
                resultUser.setPassword(resultSet.getString("password"));
                resultUser.setUserTimestamp(resultSet.getTimestamp("userTimestamp"));
                resultUser.setActive(resultSet.getBoolean("active"));
            }
        }
        return resultUser;
    }

    
}
