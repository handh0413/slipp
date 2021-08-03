package net.slipp.user;

import javax.servlet.http.HttpSession;

public class SessionUtils {
	public static boolean isEmpty(HttpSession session, String key) {
		Object object = session.getAttribute(key);
		return object == null ? true : false;
	}
	
	public static String getStringValue(HttpSession session, String key) {
		if (!isEmpty(session, key)) {
			return null;
		}
		return (String) session.getAttribute(key);
	}
}
