<%@tag description="Wrapper Tag" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="UTF-8">
	<title>Extremely Vulnerable Blog</title>
	<c:if test="${isCaptchaEnabled}">
	<script src="https://www.google.com/recaptcha/api.js" async defer></script>
	</c:if>
</head>
<body>
<header>
   <a href="/home">Home</a>
   <a href="/login">Login</a>
</header>
	<jsp:doBody/>
<footer>
<div>
<p>
XVulB 2018
</p>
</div>
</footer>
</body>
</html>