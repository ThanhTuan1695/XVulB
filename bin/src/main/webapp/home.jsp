<%@ page language="java" contentType="text/html" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List"%>

<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="UTF-8">
	<title>Extremely Vulnerable Blog</title>
</head>
<body>
	<h1>Logged-in users - Home page</h1>
	
	<h2>List of users</h2>

        <table border="1">
            <tr>
                <th>Id</th>
                <th>Username</th>
                <th>Password</th>
                <th>Firstname</th>
                <th>Lastname</th>
                
            </tr>
            <c:forEach items="${users}" var="user">
            <tr>
                <td><c:out value="${user.id}" /></td>
                <td><c:out value="${user.username}" /></td>
                <td><c:out value="${user.password}" /></td>
                <td><c:out value="${user.firstname}" /></td>
                <td><c:out value="${user.lastname}" /></td>
            </tr>
            </c:forEach>
            
        </table>
</body>
</html>