<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="net.slipp.user.User" %>
<%@ page import="net.slipp.db.Database" %>
<%
	String userId = request.getParameter("userId");
	String password = request.getParameter("password");
	
	User user = Database.findById(userId);
	if (user == null) {
		// 사용자가 존재하지 않는다는 것을 에러 메시지로 출력한다.
	}
	
	if (password.equals(user.getPassword())) {
		// 로그인 처리한다.
	}
%>