package com.eventssystem.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eventssystem.model.User;
import com.eventssystem.model.Event;
import com.eventssystem.service.EventService;
import com.eventssystem.service.RegistrationService;


@WebServlet("/event_unregistration")
public class EventUnregistrationControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		Event eventCopy = null;
		User loggedUser = (User) request.getSession().getAttribute("user");
		if(loggedUser != null) {
			long userId  = loggedUser.getUserId();
			long eventId = Long.parseLong(request.getParameter("event_id"));
			try {
				deleteRegistration(eventId, userId);							
				eventCopy = getEvent(eventId);
				updateEvent(eventCopy);
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
		response.sendRedirect(request.getContextPath() + "/");
	}
	
	private void deleteRegistration(long eventId, long userId) throws SQLException {
		RegistrationService registrationService = new RegistrationService();
		registrationService.delRegistration(eventId, userId);
	}
		
	private void updateEvent(Event eventCopy) throws SQLException {						
		EventService eventService = new EventService();
		eventService.updateEventAttendeesDown(eventCopy);
	}	
		
	private Event getEvent(long eventId) throws SQLException {
		Event eventCopy = null;
		EventService eventService = new EventService();
		eventCopy = eventService.getEventById(eventId);
		return eventCopy;
	}
	
}