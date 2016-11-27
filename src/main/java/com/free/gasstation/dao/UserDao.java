/**
 * Createed On: Oct 24, 2016 12:31:10 PM
 * Created By: mohamed.morsy
 */
package com.free.gasstation.dao;

import java.sql.Connection;

import com.free.gasstation.dao.base.BaseDao;
import com.free.gasstation.dto.User;

/**
 * @author mohamed.morsy
 *
 */
public interface UserDao extends BaseDao {

	public boolean addUser(Connection connection, User user);

	public boolean updateUser(Connection connection, User user);

	public boolean isUsernameExits(Connection connection, String username);

	public boolean isEmailExits(Connection connection, String email);

	public User findUserByUsername(Connection connection, String username);

	public boolean isEmailExits(Connection connection, String email, int id);

	public boolean isUsernameExits(Connection connection, String username, int id);

}
