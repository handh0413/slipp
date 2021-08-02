package net.slipp.user;

import static org.junit.Assert.*;

import org.junit.Test;

import net.slipp.db.Database;

public class UserTest {
	public static User TEST_USER = new User("donghee.han", "1234", "한동희", "donghee.han@samsung.com");

	@Test
	public void matchPassword() {
		boolean result = TEST_USER.matchPassword("1234");
		assertTrue(result);
	}
	
	@Test
	public void notMatchPassword() {
		boolean result = TEST_USER.matchPassword("4321");
		assertFalse(result);
	}
	
	@Test
	public void login() throws Exception {
		User user = TEST_USER;
		Database.addUser(user);
		assertTrue(User.login(TEST_USER.getUserId(), TEST_USER.getPassword()));
	}
	
	@Test(expected = UserNotFoundException.class)
	public void loginWhenNotExistedUser() throws Exception {
		User.login("userId2", TEST_USER.getPassword());
	}
	
	@Test(expected = PasswordMismatchException.class)
	public void loginWhenPasswordMismatch() throws Exception {
		User user = TEST_USER;
		Database.addUser(user);
		User.login(TEST_USER.getUserId(), "4321");
	}

}
