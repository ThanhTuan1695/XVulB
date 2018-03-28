<%@ page language="java" contentType="text/html" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper>
	<div class="loginpage text-center">
	
		<form class="form-login" action="/signup" method="post">
			<h1 class='h3 mb-3 font-weight-normal'>Register form</h1>
			<input type="text" name="username" class="form-control" placeholder="username" required>
			<input type="password" name="password" class="form-control" placeholder="password" required>
			<input type="password" name="re-password" class="form-control" placeholder="re-enter password" required>
			<input type="text" name="firstname" class="form-control" placeholder="your first name" required>
			<input type="text" name="lastname" class="form-control" placeholder="your last name" required>
			<c:if test="${isCaptchaEnabled}">
				<div class="g-recaptcha" data-sitekey="6LefDkwUAAAAAH20_0jZI2JNic67TnXNgXX1Ge75"></div>
			</c:if>
			<%-- <%

	            String password = "" + request.getParameter("password");
	            String rePassword = "" + request.getParameter("re-password");
	          if (!password.equals(rePassword)){
	        	  	model.addAttribute("errorMessage", "Password does not match the confirm password");
        		%> --%>
			<br>
			<button class="btn btn-lg btn-primary btn-block" type="submit">Sign up</button>
			<c:if test="${not empty errorMessage}">
				<p style="color:red">${errorMessage}</p>
			</c:if>
		
		</form>
	</div>
</t:wrapper>
	