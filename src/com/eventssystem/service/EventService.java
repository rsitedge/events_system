package com.eventssystem.service;

import java.sql.SQLException;

import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

import com.eventssystem.dao.EventDao;
import com.eventssystem.model.Event;
import com.eventssystem.model.User;

import java.sql.Timestamp;
import java.time.*;

public class EventService {

		public void addEvent(String name, String description, LocalDate date, LocalTime time, String address, int maxAttendees, User user) throws SQLException {
			Event event = createEvent(name, description, date, time, address, maxAttendees, user);
			EventDao eventDao = new EventDao();
			eventDao.create(event);
		}
	
		private Event createEvent(String name, String description, LocalDate date, LocalTime time, String address, int maxAttendees, User user) {
			Event event = new Event();
			event.setName(name);
			event.setDescription(description);
			event.setDate(date);
			event.setTime(time);
			event.setAddress(address);
			event.setMaxAttendees(maxAttendees);
			event.setEventTimestamp(new Timestamp(new java.util.Date().getTime()));
			event.setUserId(user.getUserId());
			User userCopy = new User(user);
			event.setUser(userCopy);
			return event;
		}

		public List<Event> getEvents() throws SQLException {
			return getEvents(null);
		}
		
		public List<Event> getEvents(Comparator<Event> comparator) throws SQLException {
			EventService eventService = new EventService();					
			List<Event> events = eventService.getCurrentEvents();			
			if(comparator != null && events != null) {											
				events.sort(comparator);														
			}
			return events;
		}
		
		private List<Event> getCurrentEvents() throws SQLException {
			List<Event> currentEvents = new ArrayList<>();
			EventDao eventDao = new EventDao();
			List<Event> allEvents = eventDao.getAllEvents();		
			LocalDate currentDate = LocalDate.now();
			for (Event event : allEvents) {
				if (event.getDate().isAfter(currentDate)) {
				currentEvents.add(event);
				}
			}
			return currentEvents;
		}

		public List<Event> getArchEvents() throws SQLException {
			return getArchEvents(null);
		}
		
		public List<Event> getArchEvents(Comparator<Event> comparator) throws SQLException {
			EventService eventService = new EventService();					
			List<Event> events = eventService.getOldEvents();			
			if(comparator != null && events != null) {											
				events.sort(comparator);														
			}
			return events;
		}
		
		private List<Event> getOldEvents() throws SQLException {
			List<Event> oldEvents = new ArrayList<>();
			EventDao eventDao = new EventDao();
			List<Event> allEvents = eventDao.getAllEvents();		
			LocalDate currentDate = LocalDate.now();
			for (Event event : allEvents) {
				if (event.getDate().isBefore(currentDate)) {
				oldEvents.add(event);
				}
			}
			return oldEvents;
		}

		public List<Event> getSearchedEvents() throws SQLException {
			return getSearchedEvents(null, null);
		}
		
		public List<Event> getSearchedEvents(String search, Comparator<Event> comparator) throws SQLException {
			EventService eventService = new EventService();					
			List<Event> events = eventService.getFoundEvents(search);			
			if(comparator != null && events != null) {											
				events.sort(comparator);														
			}
			return events;
		}
		
		private List<Event> getFoundEvents(String search) throws SQLException {
			List<Event> foundEvents = new ArrayList<>();
			EventDao eventDao = new EventDao();
			List<Event> searchedEvents = eventDao.getSearchedEvents(search);		
			LocalDate currentDate = LocalDate.now();
			for (Event event : searchedEvents) {
				if (event.getDate().isAfter(currentDate)) {
				foundEvents.add(event);
				}
			}
			return foundEvents;
		}
		
		public Event getEventById(long eventId) throws SQLException {
			Event eventCopy = null;
			EventDao eventDao = new EventDao();
			eventCopy = eventDao.read(eventId);
			return eventCopy;
		}
		
		public void updateEventAttendeesUp(Event eventCopy) throws SQLException {
			EventDao eventDao = new EventDao();
			eventDao.updateUp(eventCopy);
		}	

		public void updateEventAttendeesDown(Event eventCopy) throws SQLException {
			EventDao eventDao = new EventDao();
			eventDao.updateDown(eventCopy);
		}	

		public Event getEventByName(String name) throws SQLException {
			Event eventCopy = null;
			EventDao eventDao = new EventDao();
			eventCopy = eventDao.readByName(name);
			return eventCopy;
		}
		
}

