<%@ page language="java" contentType="text/html" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper>
	<div class="loginpage text-center">
	
		<form class="form-login" action="/login" method="post">
			<h1 h3 mb-3 font-weight-normal>Please login</h1>
			<label for="username" class="sr-only">Username</label>
			<input type="username" name="username" class="form-control" placeholder="username" required>
			<label for="password" class="sr-only">Password</label>
			<input type="password" name="password" class="form-control" placeholder="password" required>
			
			<c:if test="${isCaptchaEnabled}">
				<div class="g-recaptcha" data-sitekey="6LefDkwUAAAAAH20_0jZI2JNic67TnXNgXX1Ge75"></div>
			</c:if>
			
			<br>
			<button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
			<c:if test="${not empty errorMessage}">
				<p style="color:red">${errorMessage}</p>
			</c:if>
		
		</form>
	</div>
</t:wrapper>
	