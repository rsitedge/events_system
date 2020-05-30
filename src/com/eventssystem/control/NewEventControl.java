package com.eventssystem.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.time.*;

import com.eventssystem.model.User;
import com.eventssystem.model.Event;
import com.eventssystem.service.EventService;

	@WebServlet("/new_event")
	public class NewEventControl extends HttpServlet {
		private static final long serialVersionUID = 1L;

		@Override
		protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			if(request.getUserPrincipal() != null) {
				request.getRequestDispatcher("WEB-INF/new_event.jsp").forward(request, response);
			} else {
				response.sendError(403);
			}
		}

		@Override
		protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			request.setCharacterEncoding("UTF-8");
			String name = request.getParameter("inputName");
			String description = request.getParameter("inputDescription");
			String address = request.getParameter("inputLocation");
			
			String dateString = request.getParameter("inputDate");
			LocalDate date = LocalDate.parse(dateString);
			
			String timeString = request.getParameter("inputTime");
			LocalTime time = LocalTime.parse(timeString);
			
			int maxAttendees = Integer.parseInt(request.getParameter("inputMaxAttendees"));
			User authenticatedUser = (User) request.getSession().getAttribute("user");
			if(request.getUserPrincipal() != null) {
				EventService eventService = new EventService();
				Event existedEvent = null;;
				try {
					existedEvent = eventService.getEventByName(name);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				if (existedEvent != null) {
					request.getRequestDispatcher("WEB-INF/new_event_error.jsp").forward(request, response);
				} else {
					try {
						eventService.addEvent(name, description, date, time, address, maxAttendees, authenticatedUser);
					} catch (SQLException e) {
						e.printStackTrace();
					}
					response.sendRedirect(request.getContextPath() + "/");
				}
			} else {
				response.sendError(403);
			}
		}
}
