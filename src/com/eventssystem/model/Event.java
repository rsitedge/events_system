package com.eventssystem.model;

import java.time.*;
import java.sql.Timestamp;

public class Event {

	private long eventId;
	private String name;
	private String description;
	private LocalDate date;
	private LocalTime time;
	private String address;
	private int maxAttendees;
	private int attendees;
	private Timestamp eventTimestamp;
	private long userId;
	private User user;
	
	public Event() {
		super();
	}

	public Event(long eventId, String name, String description, LocalDate date, LocalTime time, String address,
			int maxAttendees, int attendees, Timestamp eventTimestamp, long userId, User user) {
		this.eventId = eventId;
		this.name = name;
		this.description = description;
		this.date = date;
		this.time = time;
		this.address = address;
		this.maxAttendees = maxAttendees;
		this.attendees = attendees;
		this.eventTimestamp = eventTimestamp;
		this.userId = userId;
		this.user = user;
	}

	public long getEventId() {
		return eventId;
	}

	public void setEventId(long eventId) {
		this.eventId = eventId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getMaxAttendees() {
		return maxAttendees;
	}

	public void setMaxAttendees(int maxAttendees) {
		this.maxAttendees = maxAttendees;
	}

	public int getAttendees() {
		return attendees;
	}

	public void setAttendees(int attendees) {
		this.attendees = attendees;
	}

	public Timestamp getEventTimestamp() {
		return eventTimestamp;
	}

	public void setEventTimestamp(Timestamp eventTimestamp) {
		this.eventTimestamp = eventTimestamp;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Event [eventId=" + eventId + ", name=" + name + ", description=" + description + ", date=" + date
				+ ", time=" + time + ", address=" + address + ", maxAttendees=" + maxAttendees + ", attendees="
				+ attendees + ", eventTimestamp=" + eventTimestamp + ", userId=" + userId + ", user=" + user + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + attendees;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + (int) (eventId ^ (eventId >>> 32));
		result = prime * result + ((eventTimestamp == null) ? 0 : eventTimestamp.hashCode());
		result = prime * result + maxAttendees;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Event other = (Event) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (attendees != other.attendees)
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (eventId != other.eventId)
			return false;
		if (eventTimestamp == null) {
			if (other.eventTimestamp != null)
				return false;
		} else if (!eventTimestamp.equals(other.eventTimestamp))
			return false;
		if (maxAttendees != other.maxAttendees)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

}
