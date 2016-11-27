package com.free.gasstation.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.free.gasstation.service.UserService;
import com.free.gasstation.util.constant.Constants;

@WebServlet("/ValidationServlet")
public class ValidationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private UserService userService = null;

	public ValidationServlet() {
		super();
		userService = new UserService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String type = request.getParameter(Constants.TYPE);
		String userId = request.getParameter("userId");

		if (type.equals(Constants.USERNAME)) {
			String username = request.getParameter(Constants.USERNAME);
			boolean flag = false;

			if (userId == null)
				flag = userService.isUsernameExits(username);
			else
				flag = userService.isUsernameExits(username, Integer.parseInt(userId));

			response.setStatus(HttpServletResponse.SC_OK);
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write((flag ? "true" : "false"));
		} else if (type.equals(Constants.EMAIL)) {
			String email = request.getParameter(Constants.EMAIL);
			boolean flag = false;

			if (userId == null)
				flag = userService.isEmailExits(email);
			else
				flag = userService.isEmailExits(email, Integer.parseInt(userId));

			response.setStatus(HttpServletResponse.SC_OK);
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write((flag ? "true" : "false"));
		}
	}
}
