<%@ page language="java" contentType="text/html" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper2>

<div class="container">
<div class="row">
<h3 class="pb-3 mb-4 font-italic border-bottom">Welcome, <b style="color:blue">${username}</b></h3>
</div>
 <div class="row">	
  <div class="col-md-4">	
  <div class="card">
    <div class="card-header" id="headingOne">
      <h5 class="mb-0">
          Change password
      </h5>
    </div>

        <div class="card-body">
        <form action="/password" method="post">
        	  <input type="hidden" name="username" value="${username}">
		  <div class="form-group">
		    <label for="exampleInputPassword1">New Password</label>
		    <input type="password" class="form-control" name="password1" id="exampleInputPassword1" placeholder="Password">
		  </div>
		  <div class="form-group">
		    <label for="exampleInputPassword2">Verify</label>
		    <input type="password" class="form-control" name="password2" id="exampleInputPassword2" placeholder="Password">
		  </div>
		  <button type="submit" class="btn btn-primary">Submit</button>
		</form>
      </div>
	</div>
	</div>
	<div class="col-md-8">
	<div class="card">
	   <div class="card-header" id="headingTwo">
	      <h5 class="mb-0">
	        New post
	      </h5>
	    </div>
	       <div class="card-body">
	       <form action="/post" method="post">
	          <input type="hidden" name="username" value="${username}">
			  <div class="form-group">
			    <label for="post-title">Title</label>
			    <input type="text" class="form-control" name="post-title" id="post-title" placeholder="post title">
			  </div>
		
			  <div class="form-group">
			    <label for="post-content">Content</label>
			    <textarea class="form-control" id="post-content" name="post-content" rows="4"></textarea>
			  </div>
			  <button type="submit" class="btn btn-primary">Submit</button>
			</form> 
	       </div>
	</div>
	</div>
  </div>
 </div> 
<br>
<div class="container panel">
<div class="row">
<h3 class="pb-3 mb-4 font-italic border-bottom">List of users</h3>
</div>

          <div class="table-responsive">
            <table class="table table-striped table-sm">

            <tr>
                <th>Id</th>
                <th>Username</th>
                <th>Password (${pwstorage}) </th>
                <th>Salt</th>
                <th>Firstname</th>
                <th>Lastname</th>
                
            </tr>
            <c:forEach items="${users}" var="user">
            <tr>
                <td><c:out value="${user.id}" /></td>
                <td><c:out value="${user.username}" /></td>
                <td><c:out value="${user.password}" /></td>
                <td><c:out value="${user.salt}" /></td>
                <td><c:out value="${user.firstname}" /></td>
                <td><c:out value="${user.lastname}" /></td>
            </tr>
            </c:forEach>
            
        </table>
        </div>

</div>
<div></div>
</t:wrapper2>