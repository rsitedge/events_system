package com.eventssystem.service;

import com.eventssystem.dao.UserDao;
import com.eventssystem.model.User;

import java.sql.SQLException;
import java.sql.Timestamp;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UserService {

	public void addUser(String username, String email, String password) throws SQLException {
		User user = new User();
		user.setUsername(username);
		user.setEmail(email);
		user.setActive(true);
		String md5Pass = encryptPassword(password);
		user.setPassword(md5Pass);
		user.setUserTimestamp(new Timestamp(new java.util.Date().getTime()));
		UserDao userDao = new UserDao();
		userDao.create(user);
	}
	
	private String encryptPassword(String password) {
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		digest.update(password.getBytes());
		String md5Password = new BigInteger(1, digest.digest()).toString(16);
		return md5Password;
	}
	
	public User getUserById(long userId) throws SQLException {
		UserDao userDao = new UserDao();
		User user = userDao.read(userId);
		return user;
	}
	
	public User getUserByUsername(String username) throws SQLException {
		UserDao userDao = new UserDao();
		User user = userDao.getUserByUsername(username);
		return user;
	}
	
	public boolean verifiUser(String username, String email) throws SQLException {
		boolean check = false;
		String verifiEmail = null;
		User user = getUserByUsername(username);
		verifiEmail = user.getEmail();
		if (verifiEmail.equals(email)) {
			check = true;
		}
		return check;	
	}
	
}
