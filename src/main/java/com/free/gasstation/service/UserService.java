package com.free.gasstation.service;

import java.sql.Connection;

import org.apache.log4j.Logger;

import com.free.gasstation.dao.UserDao;
import com.free.gasstation.dao.impl.jdbc.UserDaoImpl;
import com.free.gasstation.dto.User;
import com.free.gasstation.exception.NotUniqueEmailException;
import com.free.gasstation.exception.NotUniqueUserNameException;
import com.free.gasstation.service.base.BaseService;
import com.free.gasstation.util.DBConnectionManager;
import com.free.gasstation.util.constant.Constants;

public class UserService extends BaseService {

	private final Logger logger = Logger.getLogger(UserService.class);

	private static final long serialVersionUID = 1L;

	private UserDao userDao;

	public UserService() {
		userDao = (UserDao) new UserDaoImpl();
	}

	public User register(User user) throws NotUniqueUserNameException, NotUniqueEmailException {

		Connection connection = null;
		try {
			connection = DBConnectionManager.getConnectionFromPool();

			if (userDao.isUsernameExits(connection, user.getUsername())) {
				NotUniqueUserNameException e = new NotUniqueUserNameException(
						"Creating user failed, " + user.getUsername() + " Already Exists");
				logger.error(e);
				throw e;
			}

			if (userDao.isEmailExits(connection, user.getEmail())) {
				NotUniqueEmailException e = new NotUniqueEmailException(
						"Creating user failed, " + user.getEmail() + " Already Exists");
				logger.error(e);
				throw e;
			}

			if (userDao.addUser(connection, user)) {
				user.setStatus(Constants.SUCCESS);
				return user;
			}

		} catch (InterruptedException e) {
			logger.error(e);
			user.setStatus(Constants.FAIL);
		} finally {
			DBConnectionManager.backConnectionToPool(connection);
		}
		return user;
	}

	public User login(String username, String password) {
		User user = null;
		Connection connection = null;
		try {
			connection = DBConnectionManager.getConnectionFromPool();

			user = userDao.findUserByUsername(connection, username);

			if (user != null && !user.getPassword().equals(password))
				user = null;
		} catch (InterruptedException e) {
			logger.error(e);
		} finally {
			DBConnectionManager.backConnectionToPool(connection);
		}
		return user;
	}

	public boolean updateUser(User user) throws NotUniqueUserNameException, NotUniqueEmailException {
		Connection connection = null;
		try {
			connection = DBConnectionManager.getConnectionFromPool();

			if (userDao.isUsernameExits(connection, user.getUsername(), user.getId())) {
				NotUniqueUserNameException e = new NotUniqueUserNameException(
						"Creating user failed, " + user.getUsername() + " Already Exists");
				logger.error(e);
				throw e;
			}

			if (userDao.isEmailExits(connection, user.getEmail(), user.getId())) {
				NotUniqueEmailException e = new NotUniqueEmailException(
						"Creating user failed, " + user.getEmail() + " Already Exists");
				logger.error(e);
				throw e;
			}
			return userDao.updateUser(connection, user);

		} catch (InterruptedException e) {
			logger.error(e);
			return false;
		} finally {
			DBConnectionManager.backConnectionToPool(connection);
		}
	}

	public User activate(String email, String code) throws NotUniqueUserNameException, NotUniqueEmailException {
		User user = null;
		Connection connection = null;
		try {
			connection = DBConnectionManager.getConnectionFromPool();

			user = userDao.findUserByEmail(connection, email);

			if (user != null && !user.getActive() && user.getId() == Integer.parseInt(code)) {
				user.setActive(true);
				updateUser(user);
			} else
				user = null;
		} catch (InterruptedException e) {
			logger.error(e);
		} finally {
			DBConnectionManager.backConnectionToPool(connection);
		}
		return user;
	}

	public boolean isUsernameExits(String username) {
		Connection connection = null;
		try {
			connection = DBConnectionManager.getConnectionFromPool();
			return userDao.isUsernameExits(connection, username);
		} catch (InterruptedException e) {
			logger.error(e);
			return false;
		} finally {
			DBConnectionManager.backConnectionToPool(connection);
		}
	}

	public boolean isEmailExits(String email) {
		Connection connection = null;
		try {
			connection = DBConnectionManager.getConnectionFromPool();
			return userDao.isEmailExits(connection, email);
		} catch (InterruptedException e) {
			logger.error(e);
			return false;
		} finally {
			DBConnectionManager.backConnectionToPool(connection);
		}
	}

	public boolean isUsernameExits(String username, int id) {
		Connection connection = null;
		try {
			connection = DBConnectionManager.getConnectionFromPool();
			return userDao.isUsernameExits(connection, username, id);
		} catch (InterruptedException e) {
			logger.error(e);
			return false;
		} finally {
			DBConnectionManager.backConnectionToPool(connection);
		}
	}

	public boolean isEmailExits(String email, int id) {
		Connection connection = null;
		try {
			connection = DBConnectionManager.getConnectionFromPool();
			return userDao.isEmailExits(connection, email, id);
		} catch (InterruptedException e) {
			logger.error(e);
			return false;
		} finally {
			DBConnectionManager.backConnectionToPool(connection);
		}
	}
}
