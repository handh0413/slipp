package net.slipp.user;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.commons.beanutils.BeanUtilsBean;

import net.slipp.util.MyValidatorFactory;

@WebServlet("/users/save")
public class CreateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		User user = new User();
		try {
			BeanUtilsBean.getInstance().populate(user, request.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e1) {
			throw new ServletException(e1);
		}
		
		request.setAttribute("user", user);
		
		Validator validator = MyValidatorFactory.createValidator();
		Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
		if (constraintViolations.size() > 0) {
			ConstraintViolation<User> constraintViolation = constraintViolations.iterator().next();
			String errorMessage = constraintViolation.getPropertyPath() + " : " + constraintViolation.getMessage();
			forwardJsp(request, response, errorMessage);
			return;
		}
		
		UserDao userDAO = new UserDao();
		try {
			userDAO.addUser(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("/");
	}
	
	private void forwardJsp(HttpServletRequest request, HttpServletResponse response, String errorMessage)
			throws ServletException, IOException {
		request.setAttribute("errorMessage", errorMessage);
		RequestDispatcher rd = request.getRequestDispatcher("/form.jsp");
		rd.forward(request, response);
	}
}
