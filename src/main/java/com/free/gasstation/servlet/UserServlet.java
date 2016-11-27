package com.free.gasstation.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.free.gasstation.dto.User;
import com.free.gasstation.exception.NotUniqueEmailException;
import com.free.gasstation.exception.NotUniqueUserNameException;
import com.free.gasstation.service.UserService;
import com.free.gasstation.util.EmailManager;
import com.free.gasstation.util.constant.Constants;

@WebServlet("/User/UserServlet")
public class UserServlet extends HttpServlet {

	private final static Logger logger = Logger.getLogger(UserServlet.class);

	private static final long serialVersionUID = 1L;

	private UserService userService;

	@Override
	public void init() throws ServletException {
		userService = new UserService();
		super.init();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String logout = request.getParameter(Constants.LOGOUT);

		System.out.println("Logout: " + logout);
		if (logout != null) {
			User user = (User) request.getSession().getAttribute(Constants.USER);

			String msg = "";
			if (user != null)
				msg = user.getUsername();
			else
				msg = "";

			response.sendRedirect("../" + Constants.INDEX + "?" + Constants.LOGOUT + "=" + msg);

			request.getSession().invalidate();
		} else
			response.sendRedirect("../" + Constants.INDEX);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String put = request.getParameter(Constants.PUT);

		if (put != null && put.equals(Constants.PUT)) {
			doPut(request, response);
			return;
		}

		if (put != null && put.equals(Constants.UPDATE)) {
			doUpdate(request, response);
			return;
		}

		String msg = Constants.ERROR;

		String username = request.getParameter(Constants.USERNAME);
		String password = request.getParameter(Constants.PASSWORD);
		String remeberMe = request.getParameter(Constants.rememberMe);

		User user = userService.login(username, password);

		if (user != null && user.getActive()) {
			request.getSession().setAttribute(Constants.USER, user);
			if (remeberMe != null && remeberMe.equals("on")) {
				Cookie c = new Cookie("userId", user.getId() + "");
				c.setMaxAge(24 * 60 * 60);
				response.addCookie(c);
			}
			msg = Constants.WELCOME;
		} else if (user != null && !user.getActive()) {
			response.sendRedirect("../" + Constants.INDEX + "?" + Constants.activation);
			return;
		}
		response.sendRedirect("../" + Constants.INDEX + "?" + msg);
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter(Constants.USERNAME);
		String[] email = request.getParameterValues(Constants.EMAIL);
		String[] password = request.getParameterValues(Constants.PASSWORD);
		String securityQuestion = request.getParameter(Constants.SECURITY_QUESTION);
		String securityAnswer = request.getParameter(Constants.SECURITY_ANSWER);
		String siteName = request.getParameter(Constants.SITE_NAME);
		String siteNumber = request.getParameter(Constants.SITE_NUMBER);

		if (password.length == 2 && !password[0].equals(password[1])) {
			logger.error("passwords not matched");
			response.sendRedirect("../" + Constants.INDEX + "?" + Constants.ERROR);
			return;
		}

		if (email.length == 2 && !email[0].equals(email[1])) {
			logger.error("emails not matched");
			response.sendRedirect("../" + Constants.INDEX + "?" + Constants.ERROR);
			return;
		}

		User user = new User(0, username, email[0], password[0], siteName, siteNumber, securityQuestion, securityAnswer,
				"");

		String msg = "";
		try {
			user = userService.register(user);
			EmailManager.send(email[0], "Activation Code", "Copy this code to activation Page " + user.getId());
			msg = "registration successfuly go to your email for activation";
		} catch (NotUniqueUserNameException e) {
			logger.error(e);
			msg = Constants.INTERNALERROR;
		} catch (NotUniqueEmailException e) {
			logger.error(e);
			msg = Constants.INTERNALERROR;
		}

		// if (user != null && user.getStatus().equals(Constants.FAIL)) {
		// request.getSession().setAttribute(Constants.USER, user);
		// msg = Constants.WELCOME;
		// }
		response.sendRedirect("../" + Constants.INDEX + "?" + msg);
	}

	private void doUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter(Constants.USERNAME);
		String[] email = request.getParameterValues(Constants.EMAIL);
		String[] password = request.getParameterValues(Constants.PASSWORD);
		String securityQuestion = request.getParameter(Constants.SECURITY_QUESTION);
		String securityAnswer = request.getParameter(Constants.SECURITY_ANSWER);
		String siteName = request.getParameter(Constants.SITE_NAME);
		String siteNumber = request.getParameter(Constants.SITE_NUMBER);

		if (password.length == 2 && password[0] != password[1]) {
			logger.error("passwords not matched");
			response.sendRedirect("../" + Constants.INDEX + "?" + Constants.ERROR);
			return;
		}

		if (email.length == 2 && email[0] != email[1]) {
			logger.error("emails not matched");
			response.sendRedirect("../" + Constants.INDEX + "?" + Constants.ERROR);
			return;
		}

		User user = null;
		User userSession = (User) request.getSession().getAttribute(Constants.USER);
		String msg = "";
		boolean flag = false;
		try {
			user = new User(userSession.getId(), username, email[0], password[0], siteName, siteNumber,
					securityQuestion, securityAnswer, "");
			flag = userService.updateUser(user);
		} catch (NotUniqueUserNameException e) {
			logger.error(e);
			msg = Constants.INTERNALERROR;
		} catch (NotUniqueEmailException e) {
			logger.error(e);
			msg = Constants.INTERNALERROR;
		}

		if (flag) {
			request.getSession().setAttribute(Constants.USER, user);
			msg = Constants.UPDATE;
		}
		response.sendRedirect("../" + Constants.INDEX + "?" + msg);
	}
}
