package com.eventssystem.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eventssystem.model.Event;
import com.eventssystem.model.Registration;
import com.eventssystem.model.User;
import com.eventssystem.service.EventService;
import com.eventssystem.service.RegistrationService;

@WebServlet("")
public class HomePageControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			saveEventsInRequest(request);
			saveUserRegistrationsInRequest(request);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("WEB-INF/index.jsp").forward(request, response);
	}

	private void saveEventsInRequest(HttpServletRequest request) throws SQLException {
		EventService eventService = new EventService();
		List<Event> events = eventService.getEvents((e1, e2) -> e2.getEventTimestamp().compareTo(e1.getEventTimestamp())); 
		request.setAttribute("events", events);
	}

	private void saveUserRegistrationsInRequest(HttpServletRequest request) throws SQLException {
		User loggedUser = (User) request.getSession().getAttribute("user");
		if(loggedUser != null) {
		long userId  = loggedUser.getUserId();
		RegistrationService registrationService = new RegistrationService();
		List<Registration> allUserRegistrations = registrationService.getAllUserRegistrations(userId); 
		request.setAttribute("userRegistrations", allUserRegistrations);
		}
	}
	
}

