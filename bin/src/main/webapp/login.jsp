<%@ page language="java" contentType="text/html" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper>
	<h1>Login page</h1>
	<form action="/login" method="post">
	<label>Username:</label><input type="text" name="username" placeholder="username">
	<br>
	<label>Password:</label><input type="password" name="password" placeholder="password">
	<input type="submit" value="login">
	<c:if test="${not empty errorMessage}">
		<p style="color:red">${errorMessage}</p>
	</c:if>
	
	</form>
</t:wrapper>
	