package com.eventssystem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.eventssystem.model.Registration;
import com.eventssystem.utility.ConnectionProvider;

public class RegistrationDao {

    private final static String CREATE = "INSERT INTO registration(registrationId, userId, eventId, registrationTimestamp) VALUES(?, ?, ?, ?);";
    private final static String READ = "SELECT registrationId, userId, eventId, registrationTimestamp FROM registration WHERE registrationId=?;";
    private final static String UPDATE = "UPDATE registration SET userId=?, eventId=? WHERE registrationId=?;";
    private final static String DELETE = "DELETE FROM registration WHERE registrationId=?;";
    private final static String READ_ALL_USER_REGISTRATIONS = "SELECT * FROM registration where userId = ?;";
    private final static String READ_REGISTRATION_BY_EVENT_ID_BY_USER_ID = "SELECT * FROM registration where userId = ? && eventId = ?;";

    public void create(Registration registration) throws SQLException {
        try (Connection conn = ConnectionProvider.getConnection();
        PreparedStatement prepStmt = conn.prepareStatement(CREATE)) {
            prepStmt.setLong(1, registration.getRegistrationId());
            prepStmt.setLong(2, registration.getUserId());
            prepStmt.setLong(3, registration.getEventId());
            prepStmt.setTimestamp(4, registration.getRegistrationTimestamp());
            prepStmt.executeUpdate();
        }
    }

    public Registration read(long registrationId) throws SQLException {
        Registration resultRegistration = null;
        try (Connection conn = ConnectionProvider.getConnection();
        PreparedStatement prepStmt = conn.prepareStatement(READ);) {
            prepStmt.setLong(1, registrationId);
            ResultSet resultSet = prepStmt.executeQuery();
            if (resultSet.next()) {
                resultRegistration = new Registration();
                resultRegistration.setRegistrationId(resultSet.getLong("registrationId"));
                resultRegistration.setUserId(resultSet.getLong("userId"));
                resultRegistration.setEventId(resultSet.getLong("eventId"));
                resultRegistration.setRegistrationTimestamp(resultSet.getTimestamp("registrationTimestamp"));
            }
        }
        return resultRegistration;
    }

    public void update(Registration registration) throws SQLException {
        try (Connection conn = ConnectionProvider.getConnection();
        PreparedStatement prepStmt = conn.prepareStatement(UPDATE);) {
            prepStmt.setLong(2, registration.getUserId());
            prepStmt.setLong(3, registration.getEventId());
            prepStmt.executeUpdate();
        }
    }

    public void delete(Registration registration) throws SQLException {
        try (Connection conn = ConnectionProvider.getConnection();
        PreparedStatement prepStmt = conn.prepareStatement(DELETE);) {
            prepStmt.setLong(1, registration.getRegistrationId());
            prepStmt.executeUpdate();
        }
    }
 
    public Registration getRegistration(long eventId, long userId) throws SQLException {
        Registration resultRegistration = null;
        try (Connection conn = ConnectionProvider.getConnection();
        PreparedStatement prepStmt = conn.prepareStatement(READ_REGISTRATION_BY_EVENT_ID_BY_USER_ID);) {
            prepStmt.setLong(1, userId);
            prepStmt.setLong(2, eventId);
            ResultSet resultSet = prepStmt.executeQuery();
            if (resultSet.next()) {
                resultRegistration = new Registration();
                resultRegistration.setRegistrationId(resultSet.getLong("registrationId"));
                resultRegistration.setUserId(resultSet.getLong("userId"));
                resultRegistration.setEventId(resultSet.getLong("eventId"));
                resultRegistration.setRegistrationTimestamp(resultSet.getTimestamp("registrationTimestamp"));
            }
        }
        return resultRegistration;
    }
    
    public List<Registration> getAllUserRegistrations(long userId) throws SQLException {
    	List<Registration> resultRegistrations = new ArrayList<>();
        try (Connection conn = ConnectionProvider.getConnection();
        PreparedStatement prepStmt = conn.prepareStatement(READ_ALL_USER_REGISTRATIONS);) {     
        	prepStmt.setLong(1, userId);
            ResultSet resultSet = prepStmt.executeQuery(); 
            while (resultSet.next()) {
                Registration registration = new Registration();
                registration.setRegistrationId(resultSet.getLong("registrationId"));
                registration.setEventId(resultSet.getLong("eventId"));
                registration.setUserId(resultSet.getLong("userId"));
                registration.setRegistrationTimestamp(resultSet.getTimestamp("registrationTimestamp"));
    			resultRegistrations.add(registration);
            }
        }
        return resultRegistrations;
    }
    
}
