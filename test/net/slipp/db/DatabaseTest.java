package net.slipp.db;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import net.slipp.user.User;
import net.slipp.user.UserTest;

public class DatabaseTest {

	@Test
	public void addAndFindWhenExisted() {
		User user = UserTest.TEST_USER;
		Database.addUser(user);
		
		User dbUser = Database.findById(user.getUserId());
		assertEquals(user, dbUser);
	}

	@Test
	public void addAndFindWhenNotExisted() {
		User dbUser = Database.findById("userId2");
		assertNull(dbUser);
	}
}
