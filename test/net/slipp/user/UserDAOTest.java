package net.slipp.user;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

public class UserDaoTest {
	private static User TEST_USER = new User("donghee.han", "1234", "한동희", "donghee.han@test.com");
	UserDao userDao;
	
	@Before
	public void setup() throws SQLException {
		userDao = new UserDao();
		userDao.removeUser(TEST_USER.getUserId());
	}
	
	@Test
	public void connection() throws Exception {
		Connection connection = userDao.getConnection();
		assertNotNull(connection);
	}
	
	@Test
	public void crud() throws Exception {
		User user = TEST_USER;
		userDao.addUser(user);

		User dbUser = userDao.findById(user.getUserId());
		assertEquals(user, dbUser);
	}
	
	@Test
	public void findWhenNotExists() throws Exception {
		User user = TEST_USER;
		User dbUser = userDao.findById(user.getUserId());
		assertNull(dbUser);
	}
}
