package com.eventssystem.model;

import java.sql.Timestamp;

public class User {
	
	private long userId;
	private String username;
	private String email;
	private String password;
	private boolean active;
	private Timestamp userTimestamp;
		
	public User() {}
		
	public User(User user) {
		this.userId = user.userId;
		this.username = user.username;
		this.email = user.email;
		this.password = user.password;
		this.active = user.active;
		this.userTimestamp = new Timestamp(user.userTimestamp.getTime());
	}
	
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean getActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Timestamp getUserTimestamp() {
		return userTimestamp;
	}

	public void setUserTimestamp(Timestamp userTimestamp) {
		this.userTimestamp = userTimestamp;
	}
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", email=" + email + ", password=" + password
				+ ", active=" + active + ", userTimestamp=" + userTimestamp + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((userTimestamp == null) ? 0 : userTimestamp.hashCode());
		result = prime * result + (int) (userId ^ (userId >>> 32));
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (active != other.active)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (userTimestamp == null) {
			if (other.userTimestamp != null)
				return false;
		} else if (!userTimestamp.equals(other.userTimestamp))
			return false;
		if (userId != other.userId)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
}
