package com.eventssystem.service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import com.eventssystem.dao.RegistrationDao;
import com.eventssystem.model.Registration;


public class RegistrationService {
	
	public void addRegistration(long eventId, long userId) throws SQLException {
		Registration registration = createRegistration(eventId, userId);
		RegistrationDao registrationDao = new RegistrationDao();
		registrationDao.create(registration);
	}

	private Registration createRegistration(long eventId, long userId) {
		Registration registration = new Registration();
		registration.setEventId(eventId);
		registration.setUserId(userId);
		registration.setRegistrationTimestamp(new Timestamp(new java.util.Date().getTime()));
		return registration;
	}
		
	public void delRegistration(long eventId, long userId) throws SQLException {
		RegistrationDao registrationDao = new RegistrationDao();
		Registration registration = registrationDao.getRegistration(eventId, userId);
		registrationDao.delete(registration);
	}
	
	public List<Registration> getAllUserRegistrations(long userId) throws SQLException {
		RegistrationDao registrationDao = new RegistrationDao();
		List<Registration> userRegistrations = registrationDao.getAllUserRegistrations(userId);
		return userRegistrations;
	}
	
}
