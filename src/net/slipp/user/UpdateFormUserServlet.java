package net.slipp.user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/users/updateForm")
public class UpdateFormUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String userId = SessionUtils.getStringValue(session, LoginServlet.SESSION_USER_ID)	;	
		if (userId == null) {
			resp.sendRedirect("/");
			return;
		}
		
		UserDao userDao = new UserDao();
		try {
			User user = userDao.findById(userId);
			req.setAttribute("user", user);
			req.setAttribute("isUpdate", true);
			RequestDispatcher rd = req.getRequestDispatcher("/form.jsp");
			rd.forward(req, resp);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
