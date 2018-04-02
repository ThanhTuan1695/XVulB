<%@ page language="java" contentType="text/html" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper>
	<div class="loginpage text-center">
	
		<form class="form-login" method="post">
			<h1 class='h3 mb-3 font-weight-normal'>Register form</h1>
			<input type="text" name="username" class="form-control" placeholder="username" required>
			<input type="password" name="password" class="form-control" placeholder="password" required>
			<input type="password" name="re-password" class="form-control" placeholder="re-enter password" required 
					onchange="validate()">
			<input type="text" name="firstname" class="form-control" placeholder="your first name" required>
			<input type="text" name="lastname" class="form-control" placeholder="your last name" required>
			<br>
			<button class="btn btn-lg btn-primary btn-block" type="submit">Sign up</button>
			<p class="error" style="color:red">
				<c:if test="${not empty errorMessage}">
					${errorMessage}
				</c:if>
			</p>
		</form>
	</div>
</t:wrapper>
	