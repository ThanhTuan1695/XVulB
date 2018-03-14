<%@ page language="java" contentType="text/html" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper>
	<h1>Sign Up</h1>
	<div class="signupform">
		<form action="/signup" method="post">
			<label><b>Username</b></label>
			<br>
			<input type="text" name="username" placeholder="username" required>
			<br>
			<label><b>Password</b></label>
			<br>
			<input type="password" name="password" placeholder="password" required>
			<br>
			<label><b>Re-enter Password</b></label>
			<br>
			<input type="password" name="repassword" placeholder="re-enter password" required>
			
			<c:if test="${isCaptchaEnabled}">
				<div class="g-recaptcha" data-sitekey="6LefDkwUAAAAAH20_0jZI2JNic67TnXNgXX1Ge75"></div>
			</c:if>
			
			<br>
			<input type="submit" value="Submit">
			<c:if test="${not empty errorMessage}">
				<p style="color:red">${errorMessage}</p>
			</c:if>
		
		</form>
	</div>
</t:wrapper>
	