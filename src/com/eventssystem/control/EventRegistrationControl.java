package com.eventssystem.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eventssystem.model.Event;
import com.eventssystem.model.User;
import com.eventssystem.service.EventService;
import com.eventssystem.service.RegistrationService;


@WebServlet("/event_registration")
public class EventRegistrationControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		Event eventCopy = null;
		User loggedUser = (User) request.getSession().getAttribute("user");
		if(loggedUser != null) {
			long userId  = loggedUser.getUserId();
			long eventId = Long.parseLong(request.getParameter("event_id"));
			try {
				newRegistration(eventId, userId);
				eventCopy = getEvent(eventId);
				updateEvent(eventCopy);
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
		response.sendRedirect(request.getContextPath() + "/");
	}

	private void newRegistration(long eventId, long userId) throws SQLException {
		RegistrationService registrationService = new RegistrationService();
		registrationService.addRegistration(eventId, userId);
	}
		
	private void updateEvent(Event eventCopy) throws SQLException {						
		EventService eventService = new EventService();
		eventService.updateEventAttendeesUp(eventCopy);
	}	
		
	private Event getEvent(long eventId) throws SQLException {
		Event eventCopy = null;
		EventService eventService = new EventService();
		eventCopy = eventService.getEventById(eventId);
		return eventCopy;
	}

}