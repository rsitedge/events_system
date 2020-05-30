package com.eventssystem.dao;

import java.sql.SQLException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.eventssystem.model.User;
import com.eventssystem.model.Event;
import com.eventssystem.utility.ConnectionProvider;

import java.time.*;
import java.sql.Date;
import java.sql.Time;

public class EventDao {
	
    private final static String CREATE = "INSERT INTO event(eventId, name, description, date, time, address, maxAttendees, eventTimestamp, userId, attendees) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private final static String READ = "SELECT eventId, name, description, date, time, address, maxAttendees, eventTimestamp, userId, attendees FROM event WHERE eventId=?;";
    private final static String UPDATE = "UPDATE event SET attendees=? WHERE eventId=?;";
    private final static String DELETE = "DELETE FROM event WHERE eventId=?;";
    private final static String READ_ALL_EVENTS = "SELECT user.userId, username, email, password, userTimestamp, eventId, name, description, date, time, address, maxAttendees, eventTimestamp, attendees FROM event LEFT JOIN user ON event.userId=user.userId;";
    private final static String READ_SEARCHED_EVENTS = "SELECT user.userId, username, email, password, userTimestamp, eventId, name, description, date, time, address, maxAttendees, eventTimestamp, attendees FROM event LEFT JOIN user ON event.userId=user.userId where name like ? OR description like ? OR address like ? OR user.username like ?;";
    private final static String READ_BY_NAME = "SELECT eventId, name, description, date, time, address, maxAttendees, eventTimestamp, userId, attendees FROM event WHERE name=?;";
    
    public void create(Event event) throws SQLException {
        try (Connection conn = ConnectionProvider.getConnection();
        PreparedStatement prepStmt = conn.prepareStatement(CREATE)) {
            prepStmt.setLong(1, event.getEventId());
            prepStmt.setString(2, event.getName());
            prepStmt.setString(3, event.getDescription());   
            
            LocalDate localDate = event.getDate();
            Date sqlDate = java.sql.Date.valueOf(localDate);
            prepStmt.setDate(4, sqlDate);
            
            LocalTime localTime = event.getTime();
            Time sqlTime = java.sql.Time.valueOf(localTime);
            prepStmt.setTime(5, sqlTime);
            
            prepStmt.setString(6, event.getAddress());
            prepStmt.setInt(7, event.getMaxAttendees());
            prepStmt.setTimestamp(8, event.getEventTimestamp());
            prepStmt.setLong(9, event.getUserId());
            prepStmt.setInt(10, event.getAttendees());
            prepStmt.executeUpdate();
        }
    }

    public Event read(Long eventId) throws SQLException {
        Event resultEvent = null;
        try (Connection conn = ConnectionProvider.getConnection();
        PreparedStatement prepStmt = conn.prepareStatement(READ);) {
            prepStmt.setLong(1, eventId);
            ResultSet resultSet = prepStmt.executeQuery();
            if (resultSet.next()) {
                resultEvent = new Event();
                resultEvent.setEventId(resultSet.getLong("eventId"));
                resultEvent.setName(resultSet.getString("name"));
                resultEvent.setDescription(resultSet.getString("description"));
                
                Date sqlDate = resultSet.getDate("date");
                LocalDate localDate = sqlDate.toLocalDate();
                resultEvent.setDate(localDate);
                
                Time sqlTime = resultSet.getTime("time");
                LocalTime localTime = sqlTime.toLocalTime();
                resultEvent.setTime(localTime);
                
                resultEvent.setAddress(resultSet.getString("address"));
                resultEvent.setMaxAttendees(resultSet.getInt("maxAttendees"));
                resultEvent.setEventTimestamp(resultSet.getTimestamp("eventTimestamp"));
                resultEvent.setUserId(resultSet.getLong("userId"));
                resultEvent.setAttendees(resultSet.getInt("attendees"));
            }
        }
        return resultEvent;
    }

    public void updateUp(Event eventCopy) throws SQLException {
        try (Connection conn = ConnectionProvider.getConnection();
        PreparedStatement prepStmt = conn.prepareStatement(UPDATE);) { 
              prepStmt.setInt(1, eventCopy.getAttendees() + 1);
              prepStmt.setLong(2, eventCopy.getEventId());				
              prepStmt.executeUpdate();
        }
    }
    
    public void updateDown(Event eventCopy) throws SQLException {
        try (Connection conn = ConnectionProvider.getConnection();
        PreparedStatement prepStmt = conn.prepareStatement(UPDATE);) { 
              prepStmt.setInt(1, eventCopy.getAttendees() - 1);
              prepStmt.setLong(2, eventCopy.getEventId());				
              prepStmt.executeUpdate();
        }
    }
      
    public void delete(Event event) throws SQLException {
        try (Connection conn = ConnectionProvider.getConnection();
        PreparedStatement prepStmt = conn.prepareStatement(DELETE);) {
            prepStmt.setLong(1, event.getEventId());
            prepStmt.executeUpdate();
        }
    }
    
    public List<Event> getAllEvents() throws SQLException {
    	List<Event> resultEvents = new ArrayList<>();
        try (Connection conn = ConnectionProvider.getConnection();
        PreparedStatement prepStmt = conn.prepareStatement(READ_ALL_EVENTS);) {      	
            ResultSet resultSet = prepStmt.executeQuery();              
            while (resultSet.next()) {
                Event event = new Event();
                event.setEventId(resultSet.getLong("eventId"));
                event.setName(resultSet.getString("name"));
                event.setDescription(resultSet.getString("description"));
                Date sqlDate = resultSet.getDate("date");
                LocalDate localDate = sqlDate.toLocalDate();
                event.setDate(localDate);
                Time sqlTime = resultSet.getTime("time");
                LocalTime localTime = sqlTime.toLocalTime();
                event.setTime(localTime);
                event.setAddress(resultSet.getString("address"));
                event.setMaxAttendees(resultSet.getInt("maxAttendees"));
                event.setEventTimestamp(resultSet.getTimestamp("eventTimestamp"));
                event.setAttendees(resultSet.getInt("attendees"));
                User user = new User();
                user.setUserId(resultSet.getLong("userId"));
                user.setUsername(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setUserTimestamp(resultSet.getTimestamp("userTimestamp"));
    			event.setUser(user);
    			resultEvents.add(event);
            }
        }
        return resultEvents;
    }

    public List<Event> getSearchedEvents(String search) throws SQLException {
    	List<Event> resultEvents = new ArrayList<>();
        try (Connection conn = ConnectionProvider.getConnection();
        PreparedStatement prepStmt = conn.prepareStatement(READ_SEARCHED_EVENTS);) {   
        	String searchParametr = "%" + search + "%";
        	prepStmt.setString(1, searchParametr);
       		prepStmt.setString(2, searchParametr);     
       		prepStmt.setString(3, searchParametr); 
       		prepStmt.setString(4, searchParametr); 
            ResultSet resultSet = prepStmt.executeQuery();              
            while (resultSet.next()) {
                Event event = new Event();
                event.setEventId(resultSet.getLong("eventId"));
                event.setName(resultSet.getString("name"));
                event.setDescription(resultSet.getString("description"));
                Date sqlDate = resultSet.getDate("date");
                LocalDate localDate = sqlDate.toLocalDate();
                event.setDate(localDate);
                Time sqlTime = resultSet.getTime("time");
                LocalTime localTime = sqlTime.toLocalTime();
                event.setTime(localTime);
                event.setAddress(resultSet.getString("address"));
                event.setMaxAttendees(resultSet.getInt("maxAttendees"));
                event.setEventTimestamp(resultSet.getTimestamp("eventTimestamp"));
                event.setAttendees(resultSet.getInt("attendees"));
                User user = new User();
                user.setUserId(resultSet.getLong("userId"));
                user.setUsername(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                user.setUserTimestamp(resultSet.getTimestamp("userTimestamp"));
    			event.setUser(user);
    			resultEvents.add(event);
            }
        }
        return resultEvents;
    }

    public Event readByName(String name) throws SQLException {
        Event resultEvent = null;
        try (Connection conn = ConnectionProvider.getConnection();
        PreparedStatement prepStmt = conn.prepareStatement(READ_BY_NAME);) {
            prepStmt.setString(1, name);
            ResultSet resultSet = prepStmt.executeQuery();
            if (resultSet.next()) {
                resultEvent = new Event();
                resultEvent.setEventId(resultSet.getLong("eventId"));
                resultEvent.setName(resultSet.getString("name"));
                resultEvent.setDescription(resultSet.getString("description"));
                
                Date sqlDate = resultSet.getDate("date");
                LocalDate localDate = sqlDate.toLocalDate();
                resultEvent.setDate(localDate);
                
                Time sqlTime = resultSet.getTime("time");
                LocalTime localTime = sqlTime.toLocalTime();
                resultEvent.setTime(localTime);
                
                resultEvent.setAddress(resultSet.getString("address"));
                resultEvent.setMaxAttendees(resultSet.getInt("maxAttendees"));
                resultEvent.setEventTimestamp(resultSet.getTimestamp("eventTimestamp"));
                resultEvent.setUserId(resultSet.getLong("userId"));
                resultEvent.setAttendees(resultSet.getInt("attendees"));
            }
        }
        return resultEvent;
    }
    
}
