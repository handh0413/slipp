package net.slipp.user;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class UserDAOTest {
	UserDAO userDao;
	private static User TEST_USER = new User("donghee.han", "1234", "한동희", "donghee.han@test.com");
	
	@Before
	public void setup() {
		userDao = new UserDAO();
	}
	
	@Test
	public void connection() throws Exception {
		Connection connection = userDao.getConnection();
		assertNotNull(connection);
	}
	
	@Test
	@Ignore
	public void insert() throws Exception {
		User user = TEST_USER;
		userDao.addUser(user);
	}
	
	@Test
	public void findById() throws Exception {
		User user = userDao.findById(TEST_USER.getUserId());
		assertNotNull(user);
	}
	
	@Test
	public void findById2() throws Exception {
		User user = userDao.findById(TEST_USER.getUserId());
		assertEquals(user, TEST_USER);
	}
}
