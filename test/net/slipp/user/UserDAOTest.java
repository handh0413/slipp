package net.slipp.user;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class UserDAOTest {
	UserDAO userDao;
	
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
		User user = new User("donghee.han2", "1234", "한동희2", "donghee.han2@test.com");
		userDao.insert(user);
	}
}
