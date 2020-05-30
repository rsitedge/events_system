package com.eventssystem.model;

import java.sql.Timestamp;

public class Registration {

	private long registrationId;
	private long userId;
	private long eventId;
	private Timestamp registrationTimestamp;
	
	public Registration() {}
	
	public Registration(Registration registration) {
		this.registrationId = registration.registrationId;
		this.userId = registration.userId;
		this.eventId = registration.eventId;
		this.registrationTimestamp = registration.registrationTimestamp;
	}
	
	public long getRegistrationId() {
		return registrationId;
	}
	public void setRegistrationId(long registrationId) {
		this.registrationId = registrationId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getEventId() {
		return eventId;
	}
	public void setEventId(long eventId) {
		this.eventId = eventId;
	}
	public Timestamp getRegistrationTimestamp() {
		return registrationTimestamp;
	}
	public void setRegistrationTimestamp(Timestamp registrationTimestamp) {
		this.registrationTimestamp = registrationTimestamp;
	}

	@Override
	public String toString() {
		return "Registration [registrationId=" + registrationId + ", userId=" + userId + ", eventId=" + eventId
				+ ", registrationTimestamp=" + registrationTimestamp + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (eventId ^ (eventId >>> 32));
		result = prime * result + (int) (registrationId ^ (registrationId >>> 32));
		result = prime * result + ((registrationTimestamp == null) ? 0 : registrationTimestamp.hashCode());
		result = prime * result + (int) (userId ^ (userId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Registration other = (Registration) obj;
		if (eventId != other.eventId)
			return false;
		if (registrationId != other.registrationId)
			return false;
		if (registrationTimestamp == null) {
			if (other.registrationTimestamp != null)
				return false;
		} else if (!registrationTimestamp.equals(other.registrationTimestamp))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

}
