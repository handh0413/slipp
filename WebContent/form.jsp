<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="./commons/_head.jspf" %>
</head>
<body>
    <%@ include file="./commons/_top.jspf" %>

	<div class="container">
		<div class="row">
			<div class="span12">
				<section id="typography">
				<div class="page-header">
				<c:choose>
					<c:when test="${isUpdate}">
					<h1>개인정보수정</h1>
					</c:when>
					<c:otherwise>
					<h1>회원가입</h1>
					</c:otherwise>
				</c:choose>
				</div>
				
				<c:set var="actionUrl" value="/users/save"/>
				<c:set var="buttonText" value="회원가입"/>
				<c:if test="${isUpdate}">
					<c:set var="actionUrl" value="/users/update"/>
					<c:set var="buttonText" value="수정"/>
				</c:if>
				
				<form class="form-horizontal" action="${actionUrl}" method="post">
				<c:if test="${requestScope.errorMessage != null}">
					<div class="control-group">
						<label class="error"><c:out value="${requestScope.errorMessage}"/></label>
					</div>
				</c:if>
					<div class="control-group">
						<label class="control-label" for="userId">사용자 아이디</label>
						<div class="controls">
						<c:choose>
							<c:when test="${empty user.userId}">
							<input type="text" name="userId" value="${user.userId}" />
							</c:when>
							<c:otherwise>
							${user.userId}
							<input type="hidden" name="userId" id="userId" value="${user.userId}"/>
							</c:otherwise>
						</c:choose>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="password">비밀번호</label>
						<div class="controls">
							<input type="password" id="password" name="password" value="${user.password}" placeholder="">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="name">이름</label>
						<div class="controls">
							<input type="text" id="name" name="name" value="${user.name}" placeholder="">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="email">이메일</label>
						<div class="controls">
							<input type="text" id="email" name="email" value="${user.email}" placeholder="">
						</div>
					</div>
					<div class="control-group">
						<div class="controls">
							<button type="submit" class="btn btn-primary">${buttonText}</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>