<%@page import="net.slipp.user.PasswordMismatchException"%>
<%@page import="net.slipp.user.UserNotFoundException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="net.slipp.user.User" %>
<%@ page import="net.slipp.db.Database" %>
<%
	String userId = request.getParameter("userId");
	String password = request.getParameter("password");
	
	try {
		User.login(userId, password);
		session.setAttribute("userId", userId);
		response.sendRedirect("/");
	} catch (UserNotFoundException e) {
		request.setAttribute("errorMessage", "존재하지 않는 사용자 입니다.");
		RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
		rd.forward(request, response);
	} catch (PasswordMismatchException e) {
		request.setAttribute("errorMessage", "패스워드가 일치하지 않습니다.");
		RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
		rd.forward(request, response);
	}
%>