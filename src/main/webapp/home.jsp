<%@ page language="java" contentType="text/html" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper>
	<h1>Logged-in users - Home page - Welcome <b style="color:blue">${username}</b> <a href="/logout">Logout</a></h1>
	
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
</t:wrapper>