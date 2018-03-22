<%@ page language="java" contentType="text/html" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper>

<h2>Welcome, <b style="color:blue">${username}</b> <a href="/logout">Logout</a></h1>
<div class="container">
<div class="row">	
  <div id="accordion" class="col-md-8 text-center">
  <div class="card">
    <div class="card-header" id="headingOne">
      <h5 class="mb-0">
        <button class="btn btn-link btn-block collapsed" data-toggle="collapse" data-target="#collapseOne" aria-expanded="false" aria-controls="collapseOne">
          Change password
        </button>
      </h5>
    </div>

    <div id="collapseOne" class="collapse" aria-labelledby="headingOne" data-parent="#accordion">
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
	<div class="card">
	   <div class="card-header" id="headingTwo">
	      <h5 class="mb-0">
	        <button class="btn btn-link btn-block collapsed" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
	          New post
	        </button>
	      </h5>
	    </div>
	    <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordion">
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
</div>

</t:wrapper>