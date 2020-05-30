package com.eventssystem.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eventssystem.service.UserService;

@WebServlet("/register")
public class RegisterControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		request.getRequestDispatcher("WEB-INF/user_register.jsp").forward(request, response);
	}
     
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String username = request.getParameter("inputUsername");
		String password = request.getParameter("inputPassword");
		String email = request.getParameter("inputEmail");
		UserService userService = new UserService();
		try {
			userService.addUser(username, email, password);
			if (checkUser(username, email)) {
				response.sendRedirect(request.getContextPath() + "/registered");
			}
		} catch (SQLException e) {
			response.sendRedirect(request.getContextPath() + "/unregistered"); 
			e.printStackTrace();
		}
		
	}

	private boolean checkUser(String username, String email) throws SQLException {
		boolean check = false;
		UserService userService = new UserService();
		check = userService.verifiUser(username, email);
		return check;	
	}
	
}